package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.AddressDto;
import com.iti.sakilaapi.model.entity.Address;

public class AddressService extends BaseService<Address, Short, AddressDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public AddressService(Class<Address> entityClass, Class<AddressDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
