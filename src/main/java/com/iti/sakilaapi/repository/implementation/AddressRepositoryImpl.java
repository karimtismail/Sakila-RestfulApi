package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Address;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.AddressRepository;

public class AddressRepositoryImpl extends BaseEntityRepositoryImpl<Address, Integer> implements AddressRepository {
    public AddressRepositoryImpl() {
        super(new TransactionalEntityManager(), Address.class);
    }

//    @Override
//    public Address saveAddress(Address address) {
//        if (findById(address.getAddressId()) != null) {
//            transactionalEntityManager.executeInTransactionWithoutResult(entityManager -> entityManager.persist(address));
//            return address;
//        }
//        return null;
//    }
}
