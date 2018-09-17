package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.repository.CustomerRepository;

@RestController
public class CustomerController {
	//private static final String hellomsg = "Hello, %s!";

	private final CustomerRepository customerRepository;

	CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	
	@PostMapping("/customers")
	ResponseEntity<Object> newCustomer(@RequestBody Customer newCustomer) {
		customerRepository.save(newCustomer);
		return new ResponseEntity<>("Customer is created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/customers")
    List<Customer> retrieveAllCustomers() {
		return customerRepository.findAll();	
	}
	
	
}