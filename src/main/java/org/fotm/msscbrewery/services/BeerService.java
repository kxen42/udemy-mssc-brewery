package org.fotm.msscbrewery.services;

import org.fotm.msscbrewery.web.model.BeerDto;

import java.util.UUID;
import org.fotm.msscbrewery.web.model.v2.BeerDtoV2;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

  BeerDto saveNewBeer(BeerDto beer);

  void updateBeer(UUID beerId, BeerDto beerDto);

  void deleteBeerById(UUID beerId);

  BeerDtoV2 saveNewBeer(BeerDtoV2 beer);
}
