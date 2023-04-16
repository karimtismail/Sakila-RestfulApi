package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.FilmActor;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.FilmActorRepository;

public class FilmActorRepositoryImpl extends BaseEntityRepositoryImpl<FilmActor, Short> implements FilmActorRepository {
    public FilmActorRepositoryImpl() {
        super(new TransactionalEntityManager(), FilmActor.class);
    }
}
