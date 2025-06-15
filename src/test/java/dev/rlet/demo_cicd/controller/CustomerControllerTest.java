package dev.rlet.demo_cicd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.rlet.demo_cicd.dto.CustomerDto;
import dev.rlet.demo_cicd.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private CustomerService customerService;

  @Test
  void testGetFirstCustomer() throws Exception {
    CustomerDto customerDto = new CustomerDto("Test User");
    when(customerService.getFirstCustomer()).thenReturn(customerDto);

    mockMvc
        .perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test User"))
        .andReturn();
  }

  @Test
  void testGetFirstCustomerWhenNoCustomers() throws Exception {
    when(customerService.getFirstCustomer()).thenThrow(new RuntimeException("No customers found"));

    mockMvc.perform(get("/api/users")).andExpect(status().isInternalServerError()).andReturn();
  }
}
