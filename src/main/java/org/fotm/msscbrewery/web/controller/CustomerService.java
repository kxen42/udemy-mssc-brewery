package org.fotm.msscbrewery.web.controller;

import org.fotm.msscbrewery.web.model.CustomerDto;
import java.util.UUID;

public interface CustomerService {

  CustomerDto getCustomerById(UUID customerId);

  CustomerDto saveNewCustomer(CustomerDto customer);

  void updateCustomer(UUID customerId, CustomerDto customerDto);

  void deleteCustomerById(UUID customerId);
}
