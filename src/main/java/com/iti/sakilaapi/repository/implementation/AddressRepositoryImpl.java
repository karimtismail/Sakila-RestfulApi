package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.entity.Address;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.AddressRepository;

public class AddressRepositoryImpl extends BaseEntityRepositoryImpl<Address, Short> implements AddressRepository {
    public AddressRepositoryImpl() {
        super(new TransactionalEntityManager(), Address.class);
    }
}
