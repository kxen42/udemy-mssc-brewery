package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import java.util.UUID;

public interface CustomerService {

  CustomerDto getCustomerById(UUID customerId);
}
