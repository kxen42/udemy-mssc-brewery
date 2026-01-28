package org.fotm.msscbrewery.services;

import org.fotm.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

  BeerDto saveNewBeer(BeerDto beer);
}
