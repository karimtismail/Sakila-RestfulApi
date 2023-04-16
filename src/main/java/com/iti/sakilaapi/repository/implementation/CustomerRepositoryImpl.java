package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Customer;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.CustomerRepository;

public class CustomerRepositoryImpl extends BaseEntityRepositoryImpl<Customer, Short> implements CustomerRepository {
    public CustomerRepositoryImpl() {
        super(new TransactionalEntityManager(), Customer.class);
    }
}
