package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Store;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.StoreRepository;

public class StoreRepositoryImpl extends BaseEntityRepositoryImpl<Store, Integer> implements StoreRepository {
    public StoreRepositoryImpl() {
        super(new TransactionalEntityManager(), Store.class);
    }

}
