package dev.rlet.demo_cicd.repository;

import dev.rlet.demo_cicd.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
