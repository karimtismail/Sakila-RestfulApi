package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.FilmCategory;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.FilmCategoryRepository;

public class FilmCategoryRepositoryImpl extends BaseEntityRepositoryImpl<FilmCategory, Short> implements FilmCategoryRepository {
    public FilmCategoryRepositoryImpl() {
        super(new TransactionalEntityManager(), FilmCategory.class);
    }
}
