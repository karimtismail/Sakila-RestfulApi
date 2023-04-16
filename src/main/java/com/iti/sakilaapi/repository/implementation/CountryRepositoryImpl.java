package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Country;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.CountryRepository;

public class CountryRepositoryImpl extends BaseEntityRepositoryImpl<Country, Short> implements CountryRepository {
    public CountryRepositoryImpl() {
        super(new TransactionalEntityManager(), Country.class);
    }
}
