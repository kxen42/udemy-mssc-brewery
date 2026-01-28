package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/api/{version}/beer")
@RestController // Extends @Controller + @ResponseBody
public class BeerController {

  private final BeerService beerService;

  public BeerController(BeerService beerService) {
    this.beerService = beerService;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(UUID beerId) {

    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @GetMapping(value = "/{beerId}", version = "v2")
  public ResponseEntity<BeerDto> getBeerById2(UUID beerId) {
    return ResponseEntity.ok(BeerDto.builder().id(beerId)
        .beerName("Black Label")
        .beerStyle("Lager")
        .upc("072720014346")
        .build());
  }

}
