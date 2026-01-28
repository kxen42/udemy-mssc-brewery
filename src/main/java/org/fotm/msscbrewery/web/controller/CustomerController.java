package org.fotm.msscbrewery.web.controller;

import org.fotm.msscbrewery.web.model.CustomerDto;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/api/{version}/customer")
@RestController
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
    return ResponseEntity.ok(customerService.getCustomerById(customerId));
  }

}
