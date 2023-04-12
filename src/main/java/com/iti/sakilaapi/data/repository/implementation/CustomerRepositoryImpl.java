package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.Customer;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.CustomerRepository;

public class CustomerRepositoryImpl extends BaseEntityRepositoryImpl<Customer, Short> implements CustomerRepository {
    public CustomerRepositoryImpl() {
        super(new TransactionalEntityManager(), Customer.class);
    }
}
