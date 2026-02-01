package org.fotm.msscbeerservice.web.controller;

import java.net.URI;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerservice.web.model.CustomerDto;
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

@Slf4j
@RequestMapping(value = "/api/{version}/customer")
@RestController
public class CustomerController {

  @Value("${server.address}")
  private String serverAddress;
  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId,
      @PathVariable String version) {
    log.info("Getting customer by id: {}, API version {}", customerId, version);
    return ResponseEntity.ok(CustomerDto.builder().id(customerId)
        .customerName("Bob")
        .build());
  }

  @PostMapping
  public ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customerDto,
      @PathVariable String version) {
    log.info("Saving new customer: {}, API version: {}", customerDto, version);
    var savedCustomerDto = CustomerDto.builder().id(UUID.randomUUID())
        .customerName(customerDto.getCustomerName())
        .build();
    var strUri = "http://%s:%s/api/%s/customer/%s".formatted(serverAddress, serverPort, version,
        savedCustomerDto.getId());
    return ResponseEntity
        .created(URI.create(strUri))
        .build();
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<Void> updateCustomer(@PathVariable UUID customerId,
      @RequestBody CustomerDto customerDto,
      @PathVariable String version) {
    log.info("Updating customer: {}, content: {}, API version: {}", customerId, customerDto,
        version);
    return ResponseEntity.noContent().build();
  }


  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable UUID customerId, @PathVariable String version) {
    log.info("Deleting customer by id: {}", customerId);
  }
}
