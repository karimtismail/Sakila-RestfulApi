package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.FilmText;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.FilmTextRepository;

public class FilmTextRepositoryImpl extends BaseEntityRepositoryImpl<FilmText, Integer> implements FilmTextRepository {
    public FilmTextRepositoryImpl() {
        super(new TransactionalEntityManager(), FilmText.class);
    }
}
