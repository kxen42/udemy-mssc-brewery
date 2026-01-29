package org.fotm.msscbrewery.web.controller;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Override
  public CustomerDto getCustomerById(UUID customerId) {
    log.info("Getting customer by id: {}", customerId);
    return CustomerDto.builder().id(UUID.randomUUID()).name("Fred Flintstone").build();
  }

  @Override
  public CustomerDto saveNewCustomer(CustomerDto customer) {
    log.info("Saving new customer: {}", customer);
    return CustomerDto.builder().id(UUID.randomUUID()).name("Barney Rubble").build();
  }

  @Override
  public void updateCustomer(UUID customerId, CustomerDto customerDto) {
    log.info("Updating customer: {}", customerDto);
  }

  @Override
  public void deleteCustomerById(UUID customerId) {
    log.info("Deleting customer by id: {}", customerId);
  }
}
