package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.FilmCategoryDto;
import com.iti.sakilaapi.model.entity.FilmCategory;

public class FilmCategoryService extends BaseService<FilmCategory, Short, FilmCategoryDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public FilmCategoryService(Class<FilmCategory> entityClass, Class<FilmCategoryDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
