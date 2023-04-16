package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.CategoryDto;
import com.iti.sakilaapi.model.entity.Category;

public class CategoryService extends BaseService<Category, Short, CategoryDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public CategoryService(Class<Category> entityClass, Class<CategoryDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
