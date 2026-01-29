package org.fotm.basicexamples.web.controller;

import java.net.URI;
import java.util.UUID;
import org.fotm.basicexamples.services.CustomerService;
import org.fotm.basicexamples.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/{version}/customer")
@RestController
public class CustomerController {

  private final CustomerService customerService;
  @Value("${server.address}")
  private String serverAddress;
  @Value("${server.port}")
  private String serverPort;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
    return ResponseEntity.ok(customerService.getCustomerById(customerId));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> saveNewCustomer(@RequestBody CustomerDto customer,
      @PathVariable String version) {

    CustomerDto savedCustomerDto = customerService.saveNewCustomer(customer);
    var strUri = "http://%s:%s/api/%s/customer/%s".formatted(serverAddress, serverPort, version,
        savedCustomerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .body(savedCustomerDto);
  }

  @PutMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
    customerService.updateCustomer(customerId, customerDto);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable UUID customerId) {
    customerService.deleteCustomerById(customerId);
  }
}
