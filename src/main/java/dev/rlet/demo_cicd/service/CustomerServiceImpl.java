package dev.rlet.demo_cicd.service;

import dev.rlet.demo_cicd.dto.CustomerDto;
import dev.rlet.demo_cicd.model.Customer;
import dev.rlet.demo_cicd.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void initData() {
        if (customerRepository.count() == 0) {
            customerRepository.save(new Customer("Demo User"));
        }
    }

    @Override
    public CustomerDto getFirstCustomer() {
        Customer customer = customerRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No customers found")); // Temporary exception handling
        return new CustomerDto(customer.getName());
    }
}
