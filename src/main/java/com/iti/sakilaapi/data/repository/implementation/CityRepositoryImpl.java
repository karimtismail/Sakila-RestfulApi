package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.City;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.CityRepository;

public class CityRepositoryImpl extends BaseEntityRepositoryImpl<City, Short> implements CityRepository {
    public CityRepositoryImpl() {
        super(new TransactionalEntityManager(), City.class);
    }
}
