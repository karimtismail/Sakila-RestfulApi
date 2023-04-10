package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.entity.City;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.CityRepository;

public class CityRepositoryImpl extends BaseEntityRepositoryImpl<City, Short> implements CityRepository {
    public CityRepositoryImpl() {
        super(new TransactionalEntityManager(), City.class);
    }
}
