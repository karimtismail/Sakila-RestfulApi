package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.InventoryDto;
import com.iti.sakilaapi.model.entity.Inventory;

public class InventoryService extends BaseService<Inventory, Short, InventoryDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public InventoryService(Class<Inventory> entityClass, Class<InventoryDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
