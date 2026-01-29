package org.fotm.basicexamples.web.controller;

import java.net.URI;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.basicexamples.services.BeerService;
import org.fotm.basicexamples.web.model.BeerDto;
import org.fotm.basicexamples.web.model.v2.BeerDtoV2;
import org.fotm.basicexamples.web.model.v2.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping(value = "/api/{version}/beer")
@RestController // Extends @Controller + @ResponseBody
public class BeerController {

  private final BeerService beerService;
  @Value("${server.address}")
  private String serverAddress;
  @Value("${server.port}")
  private String serverPort;

  public BeerController(BeerService beerService) {
    this.beerService = beerService;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(@PathVariable String version, @PathVariable UUID beerId) {

    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @GetMapping(value = "/{beerId}", version = "v2")
  public ResponseEntity<BeerDtoV2> getBeerById2(@PathVariable UUID beerId) {
    return ResponseEntity.ok(BeerDtoV2.builder().id(beerId)
        .beerName("Black Label")
        .beerStyle(BeerStyleEnum.LAGER)
        .upc("072720014346")
        .build());
  }

  @PostMapping
  public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beer,
      @PathVariable String version) {
    BeerDto savedBeerDto = beerService.saveNewBeer(beer);
    var strUri = "http://%s:%s/api/%s/beer/%s".formatted(serverAddress, serverPort, version,
        savedBeerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .body(savedBeerDto);
  }

  @PostMapping(version = "v2")
  public ResponseEntity<BeerDtoV2> saveNewBeer(@RequestBody BeerDtoV2 beer,
      @PathVariable String version) {
    log.info("TODO ObjectMapper so input can use 'Black Lager' rather than 'BLACK_LAGER' " + BeerStyleEnum.fromStyle("Black Lager"));
    BeerDtoV2 savedBeerDto = beerService.saveNewBeer(beer);
    var strUri = "http://%s:%s/api/%s/beer/%s".formatted(serverAddress, serverPort, version,
        savedBeerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .body(savedBeerDto);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
    beerService.updateBeer(beerId, beerDto);
    return ResponseEntity.noContent().build();
  }


  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable UUID beerId) {
    beerService.deleteBeerById(beerId);
  }
}
