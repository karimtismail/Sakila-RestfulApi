package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.ActorDto;
import com.iti.sakilaapi.model.entity.Actor;
import com.iti.sakilaapi.repository.implementation.ActorRepositoryImpl;
import com.iti.sakilaapi.repository.interfaces.ActorRepository;
import org.modelmapper.ModelMapper;

public class ActorService extends BaseService<Actor, Short, ActorDto> {
    private final ActorRepository actorRepository;
    private final ModelMapper mapper;

    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public ActorService(Class<Actor> entityClass, Class<ActorDto> dtoClass) {
        super(entityClass, dtoClass);
        actorRepository = new ActorRepositoryImpl();
        mapper = new ModelMapper();
    }
}