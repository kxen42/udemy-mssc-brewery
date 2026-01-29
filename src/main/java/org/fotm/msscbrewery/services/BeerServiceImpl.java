package org.fotm.msscbrewery.services;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 2019-04-20.
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

  @Override
  public BeerDto getBeerById(UUID beerId) {
    log.info("Getting beer by id: {}", beerId);
    return BeerDto.builder().id(UUID.randomUUID())
        .beerName("Schell's")
        .beerStyle("Dark Ale")
        .upc("088067055287")
        .build();
  }

  @Override
  public BeerDto saveNewBeer(BeerDto beer) {
    log.info("Saving new beer: {}", beer);
    return BeerDto.builder().id(UUID.randomUUID())
        .beerName("Excelsior Bayside Brown Ale")
        .beerStyle("Brown Ale")
        .upc("088001242026")
        .build();
  }

  @Override
  public void updateBeer(UUID beerId, BeerDto beerDto) {
    log.info("Updating beer: {}", beerDto);
    // TODO find beer by Id in repo
    // TODO replace all properties except beerId
  }

  @Override
  public void deleteBeerById(UUID beerId) {
    log.info("Deleting beer by id: {}", beerId);
    // TODO use repo to delete beer by Id; not an error if beer doesn't exist'
  }


}
