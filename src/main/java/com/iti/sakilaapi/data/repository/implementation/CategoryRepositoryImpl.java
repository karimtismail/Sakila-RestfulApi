package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.Category;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.CategoryRepository;

public class CategoryRepositoryImpl extends BaseEntityRepositoryImpl<Category, Short> implements CategoryRepository {
    public CategoryRepositoryImpl() {
        super(new TransactionalEntityManager(), Category.class);
    }
}
