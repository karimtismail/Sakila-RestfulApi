package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Film;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.FilmRepository;

public class FilmRepositoryImpl extends BaseEntityRepositoryImpl<Film, Integer> implements FilmRepository {
    public FilmRepositoryImpl() {
        super(new TransactionalEntityManager(), Film.class);
    }
}
