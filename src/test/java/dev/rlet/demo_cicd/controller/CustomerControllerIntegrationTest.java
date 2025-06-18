package dev.rlet.demo_cicd.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.rlet.demo_cicd.model.Customer;
import dev.rlet.demo_cicd.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CustomerControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private CustomerRepository customerRepository;

  @BeforeEach
  void setUp() {
    customerRepository.deleteAll();
  }

  @Test
  void testGetFirstCustomer() throws Exception {
    Customer customer = new Customer("Test User");
    customerRepository.save(customer);

    mockMvc
        .perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test User"))
        .andReturn();
  }

  @Test
  void testGetFirstCustomerWhenNoCustomers() throws Exception {
    mockMvc.perform(get("/api/users")).andExpect(status().isInternalServerError()).andReturn();
  }
}
