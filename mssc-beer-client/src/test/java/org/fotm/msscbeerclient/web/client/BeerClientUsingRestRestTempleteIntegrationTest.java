package org.fotm.msscbeerclient.web.client;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

/**
 * The server must be running on localhost:8080
 */
@Slf4j
@SpringBootTest
class BeerClientUsingRestTemplateIntegrationTest {

  @Autowired
  BeerClientUsingRestTemplate beerClient;

  @Test
  void getBeerById() {
    var beerById = beerClient.getBeerById(UUID.randomUUID());

    assertThat(beerById).isNotNull();
    log.info("----- getBeerById Beer: {}", beerById);
  }

  @Test
  void saveNewBeer() {
    // recall that server side is using an enum for beerStyle
    var beerDto = BeerDto.builder().beerName("Schell's").beerStyle("DARK_LAGER").upc("088067055287")
        .build();
    var uri = beerClient.saveNewBeer(beerDto);

    assertThat(uri).isNotNull();
    log.info("----- saveNewBeer Beer: {}", uri);

    // beerStyle will not be mapped to BeerStyleEnum on server side
    assertThatThrownBy(
        () -> beerClient.saveNewBeer(
            BeerDto.builder().beerName("Black Label").beerStyle("Lager").upc("072720014346")
                .build())
    ).isInstanceOf(HttpClientErrorException.class);
  }

  @Test
  void updateBeer() {
    var beerDto = BeerDto.builder().beerName("Schell's").beerStyle("DARK_LAGER").upc("088067055287").build();

    // the backend is stubbed out so there is not much else to test here; at least we didn't get 4xx or 5xx
    assertThatCode(() -> beerClient.updateBeer(UUID.randomUUID(), beerDto)).doesNotThrowAnyException();
  }

  @Test
  void deleteBeer() {

    // the backend is stubbed out so there is not much else to test here; at least we didn't get 4xx or 5xx
    beerClient.deleteBeer(UUID.randomUUID());
  }
}
