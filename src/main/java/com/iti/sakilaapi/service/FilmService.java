package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.FilmDto;
import com.iti.sakilaapi.model.entity.Film;

public class FilmService extends BaseService<Film, Short, FilmDto> {
    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public FilmService(Class<Film> entityClass, Class<FilmDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
