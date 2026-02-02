package org.fotm.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.fotm.msscbeerservice.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

  MockMvc mockMvc;

  @Autowired
  WebApplicationContext wac;
  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void getCustomerById() throws Exception {
    mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void saveNewCustomer() throws Exception {
    CustomerDto customerDto = CustomerDto.builder()
        .customerName("Wilma Flintstone")
        .build();
    String customerDtoJson = objectMapper.writeValueAsString(customerDto);

    mockMvc.perform(post("/api/1/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerDtoJson))
        .andExpect(status().isCreated());
  }

  @Test
  void updateCustomerById() throws Exception {
    CustomerDto customerDto = CustomerDto.builder()
        .customerName("Updated Customer")
        .build();
    String customerDtoJson = objectMapper.writeValueAsString(customerDto);

    mockMvc.perform(put("/api/1/customer/" + UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerDtoJson))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteCustomerById() throws Exception {
    mockMvc.perform(delete("/api/v1/customer/" + UUID.randomUUID())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
