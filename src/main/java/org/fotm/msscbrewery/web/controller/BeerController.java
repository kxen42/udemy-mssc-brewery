package org.fotm.msscbrewery.web.controller;

import org.fotm.msscbrewery.services.BeerService;
import org.fotm.msscbrewery.web.model.BeerDto;
import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/api/{version}/beer")
@RestController // Extends @Controller + @ResponseBody
public class BeerController {

  @Value("${server.address}")
  private String serverAddress;

  @Value("${server.port}")
  private String serverPort;

  private final BeerService beerService;

  public BeerController(BeerService beerService) {
    this.beerService = beerService;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(@PathVariable String version, @PathVariable UUID beerId) {

    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @GetMapping(value = "/v2/{beerId}")
  public ResponseEntity<BeerDto> getBeerById2(@PathVariable UUID beerId) {
    return ResponseEntity.ok(BeerDto.builder().id(beerId)
        .beerName("Black Label")
        .beerStyle("Lager")
        .upc("072720014346")
        .build());
  }

  @PostMapping
  public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beer, @PathVariable String version) {
    BeerDto savedBeerDto = beerService.saveNewBeer(beer);
    var strUri = "http://%s:%s/api/%s/beer/%s".formatted(serverAddress, serverPort, version, savedBeerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .body(savedBeerDto);
  }

}
