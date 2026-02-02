package org.fotm.msscbeerclient.web.client;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerclient.config.ApiConfig;
import org.fotm.msscbeerclient.web.model.CustomerDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CustomerClientUsingRestTemplate implements InitializingBean {

  private final RestTemplate restTemplate;
  private String apihost;
  private String version;
  private String path;

  public CustomerClientUsingRestTemplate(RestTemplateBuilder restTemplateBuilder, ApiConfig apiConfig) {
    this.restTemplate = restTemplateBuilder.build();
    this.apihost = apiConfig.apihost();
    this.version = apiConfig.version();
    this.path = apiConfig.path();
  }

  public CustomerDto getCustomerById(UUID customerId) {
    log.info("Getting customer by id: {}", customerId);
    return restTemplate.getForObject(getApiUrl() + "/" + customerId.toString(), CustomerDto.class);
  }

  public CustomerDto saveNewCustomer(CustomerDto customer) {
    log.info("Saving new customer: {}", customer);
    return restTemplate.postForObject(getApiUrl(), customer, CustomerDto.class);
  }

  public void updateCustomer(UUID customerId, CustomerDto customerDto) {
    log.info("Updating customer: {}", customerDto);
    restTemplate.put(getApiUrl() + "/" + customerId.toString(), customerDto);
  }

  public void deleteCustomer(UUID customerId) {
    log.info("Deleting customer by id: {}", customerId);
    restTemplate.delete(getApiUrl() + "/" + customerId.toString());
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("----- CustomerClient apihost: " + apihost);
    log.info("----- CustomerClient version: " + version);
    log.info("----- CustomerClient path: " + path);
    log.info("----- CustomerClient url: " + getApiUrl());

  }

  private String getApiUrl() {
    return ("%s" + path).formatted(apihost, version);
  }
}
