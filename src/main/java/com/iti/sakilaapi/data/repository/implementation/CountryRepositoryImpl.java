package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.Country;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.CountryRepository;

public class CountryRepositoryImpl extends BaseEntityRepositoryImpl<Country, Short> implements CountryRepository {
    public CountryRepositoryImpl() {
        super(new TransactionalEntityManager(), Country.class);
    }
}
