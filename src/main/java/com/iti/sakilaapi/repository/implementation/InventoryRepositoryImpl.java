package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Inventory;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.InventoryRepository;

public class InventoryRepositoryImpl extends BaseEntityRepositoryImpl<Inventory, Integer> implements InventoryRepository {
    public InventoryRepositoryImpl() {
        super(new TransactionalEntityManager(), Inventory.class);
    }
}
