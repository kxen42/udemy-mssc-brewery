package org.fotm.msscbeerservice.web.controller;

import java.net.URI;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerservice.web.model.BeerDto;
import org.fotm.msscbeerservice.web.model.BeerStyleEnum;
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
@RestController
public class BeerController {

  @Value("${server.address}")
  private String serverAddress;
  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
      @PathVariable String version) {
    log.info("Getting beer by id: {}, API version {}", beerId, version);
    return ResponseEntity.ok(BeerDto.builder().id(beerId)
        .beerName("Bob")
        .beerStyle(BeerStyleEnum.PILSNER)
        .upc("12341234").build());
  }

  @PostMapping
  public ResponseEntity<Void> saveNewBeer(@RequestBody BeerDto beerDto,
      @PathVariable Long version) {
    log.info("Saving new beer: {}, API version: {}", beerDto, version);
//    BeerDto savedBeerDto = beerService.saveNewBeer(beer);
    var savedBeerDto = BeerDto.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName())
        .beerStyle(beerDto.getBeerStyle())
        .upc(beerDto.getUpc()).build();
    var strUri = "http://%s:%s/api/%s/beer/%s".formatted(serverAddress, serverPort, version,
        savedBeerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .build();
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto,
      @PathVariable Long version) {
    log.info("Updating beer: {}, content: {}, API version: {}", beerId, beerDto, version);
    return ResponseEntity.noContent().build();
  }


  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable UUID beerId) {
    log.info("Deleting beer by id: {}", beerId);
  }
}
