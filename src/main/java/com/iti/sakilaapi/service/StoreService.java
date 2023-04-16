package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.StoreDto;
import com.iti.sakilaapi.model.entity.Store;

public class StoreService extends BaseService<Store, Short, StoreDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public StoreService(Class<Store> entityClass, Class<StoreDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
