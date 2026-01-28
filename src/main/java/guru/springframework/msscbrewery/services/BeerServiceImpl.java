package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class BeerServiceImpl implements BeerService {

  @Override
  public BeerDto getBeerById(UUID beerId) {
    return BeerDto.builder().id(UUID.randomUUID())
        .beerName("Schell's")
        .beerStyle("Dark Ale")
        .upc("088067055287")
        .build();
  }
}
