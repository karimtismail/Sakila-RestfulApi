package com.iti.sakilaapi.service;

import com.iti.sakilaapi.presentation.dto.CustomerDto;
import com.iti.sakilaapi.data.entity.Customer;
import com.iti.sakilaapi.data.repository.implementation.CustomerRepositoryImpl;
import com.iti.sakilaapi.data.repository.interfaces.CustomerRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public CustomerService() {
        this.customerRepository = new CustomerRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    public CustomerDto findById(Short customerId) {
        Customer customer = customerRepository.findById(customerId);
        return modelMapper.map(customer, CustomerDto.class);
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(modelMapper.map(customer, CustomerDto.class));
        }
        return customerDtos;
    }

    public CustomerDto save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDto.class);
    }

    public CustomerDto update(Customer customer) {
        Customer updatedCustomer = customerRepository.update(customer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);
    }

    public CustomerDto deleteById(Short customerId) {
        Customer deleteCustomer = customerRepository.deleteById(customerId);
        return modelMapper.map(deleteCustomer, CustomerDto.class);
    }
}
