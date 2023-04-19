package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.City;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.CityRepository;

public class CityRepositoryImpl extends BaseEntityRepositoryImpl<City, Integer> implements CityRepository {
    public CityRepositoryImpl() {
        super(new TransactionalEntityManager(), City.class);
    }
}
