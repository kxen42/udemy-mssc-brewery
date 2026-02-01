package org.fotm.msscbeerclient.web.client;

import java.net.URI;
import java.util.UUID;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerclient.config.ApiConfig;
import org.fotm.msscbeerclient.web.model.BeerDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Setter
@Slf4j
@Component
// moved to config/ApiConfig.java to avoid duplication
//@ConfigurationProperties(prefix = "beer-api", ignoreUnknownFields = false)
public class BeerClientUsingRestTemplate implements InitializingBean {


  private final RestTemplate restTemplate;
  // these don't need go in the constructor
  // property binding from application.properties
  // @Value v. @ConfigurationProperties
  // https://medium.com/@midhunbalan.dev/understanding-configurationproperties-annotation-in-springboot-674550f51f9a
  private String apihost; // these names have to match the property names <prefix>.<property-name> if not using a separate class
  private String version;
  private String path;

  public BeerClientUsingRestTemplate(RestTemplateBuilder restTemplateBuilder, ApiConfig apiConfig) {
    // this picks up the application.properties values and allows more configuration
    this.restTemplate = restTemplateBuilder.build();
    this.apihost = apiConfig.apihost();
    this.version = apiConfig.version();
    this.path = apiConfig.path();
  }

  public BeerDto getBeerById(UUID beerId) {
    log.info("Getting beer by id: {}", beerId);
    // getApiUrl() == http://localhost:8080/api/v1/beer
    return restTemplate.getForObject(getApiUrl() + "/" + beerId.toString(), BeerDto.class);
  }

  public java.net.URI saveNewBeer(BeerDto beerDto) {
    log.info("Saving new beer: {}", beerDto);
    URI uri = restTemplate.postForLocation(getApiUrl(), beerDto);
    log.info("Saved new beer with uri: {}", uri);
    return uri;
  }

  public void updateBeer(UUID beerId, BeerDto beerDto) {
    log.info("Updating beerId: {}", beerId);
    restTemplate.put(getApiUrl() + "/" + beerId.toString(), beerDto);
  }

  public void deleteBeer(UUID beerId) {
    restTemplate.delete(getApiUrl() + "/" + beerId.toString());
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("----- BeerClient apihost: " + apihost);
    log.info("----- BeerClient version: " + version);
    log.info("----- BeerClient path: " + path);
    log.info("----- BeerClient url: " + getApiUrl());
  }

  private String getApiUrl() {
    return ("%s" + path).formatted(apihost, version);
  }
}
