package dev.rlet.demo_cicd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.rlet.demo_cicd.dto.CustomerDto;
import dev.rlet.demo_cicd.model.Customer;
import dev.rlet.demo_cicd.repository.CustomerRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

  @Mock private CustomerRepository customerRepository;

  @InjectMocks private CustomerServiceImpl customerService;

  @Test
  void testGetFirstCustomerWhenCustomersExists() {
    Customer customer = new Customer("Test User");
    when(customerRepository.findAll()).thenReturn(List.of(customer));

    CustomerDto result = customerService.getFirstCustomer();

    assertEquals(
        "Test User",
        result.name(),
        "The customer name should match the first customer in the repository");
  }

  @Test
  void testGetFirstCustomerWhenNoCustomersExists() {
    when(customerRepository.findAll()).thenReturn(List.of());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> customerService.getFirstCustomer(),
            "Should throw RuntimeException when no customers are found");
    assertEquals(
        "No customers found",
        exception.getMessage(),
        "Exception message should indicate no customers");
  }
}
