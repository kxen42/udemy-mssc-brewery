package org.fotm.msscbeerclient.web.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CustomerClientUsingRestTemplateIntegrationTest {

  @Autowired
  CustomerClientUsingRestTemplate customerClient;

  @Test
  void getCustomerById() {
    var customerById = customerClient.getCustomerById(UUID.randomUUID());

    assertThat(customerById).isNotNull();
    log.info("----- getCustomerById Customer: {}", customerById);
  }

  @Test
  void saveNewCustomer() {
    var customer = CustomerDto.builder()
        .customerName("Fred Flintstone").build();

    var uri = customerClient.saveNewCustomer(customer);
    assertThat(uri).isNotNull();
    log.info("----- saveNewCustomer Customer at : {}", uri);
  }

  @Test
  void updateCustomer() {
    var customer = CustomerDto.builder()
        .customerName("Barney Rubble").build();
    // the backend is stubbed out so there is not much else to test here; at least we didn't get 4xx or 5xx

    assertThatCode(() -> customerClient.updateCustomer(UUID.randomUUID(),
        customer)).doesNotThrowAnyException();
  }

  @Test
  void deleteCustomerById() {
    // the backend is stubbed out so there is not much else to test here; at least we didn't get 4xx or 5xx
    assertThatCode(
        () -> customerClient.deleteCustomer(UUID.randomUUID())).doesNotThrowAnyException();
  }
}