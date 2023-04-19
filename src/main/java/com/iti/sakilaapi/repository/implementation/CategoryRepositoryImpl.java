package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Category;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.CategoryRepository;

public class CategoryRepositoryImpl extends BaseEntityRepositoryImpl<Category, Integer> implements CategoryRepository {
    public CategoryRepositoryImpl() {
        super(new TransactionalEntityManager(), Category.class);
    }
}
