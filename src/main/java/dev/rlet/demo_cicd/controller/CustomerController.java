package dev.rlet.demo_cicd.controller;

import dev.rlet.demo_cicd.dto.CustomerDto;
import dev.rlet.demo_cicd.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // Retrieve the first customer
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CustomerDto getFirstCustomer() {
    return customerService.getFirstCustomer();
  }
}
