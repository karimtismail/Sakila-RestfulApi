package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.Address;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.AddressRepository;

public class AddressRepositoryImpl extends BaseEntityRepositoryImpl<Address, Short> implements AddressRepository {
    public AddressRepositoryImpl() {
        super(new TransactionalEntityManager(), Address.class);
    }
}
