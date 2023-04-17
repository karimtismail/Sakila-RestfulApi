package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.ActorDto;
import com.iti.sakilaapi.model.entity.Actor;
import com.iti.sakilaapi.repository.implementation.ActorRepositoryImpl;
import com.iti.sakilaapi.repository.interfaces.ActorRepository;
import jakarta.ws.rs.NotFoundException;
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

//    public ActorDto deleteActorById(Short actorId) {
//        Actor deletedActor = actorRepository.findById(actorId);
//        if (deletedActor == null) {
//            throw new NotFoundException("Actor not found with id " + actorId);
//        }
//        actorRepository.deleteById(actorId);
//        ActorDto actorDto = mapper.map(deletedActor, ActorDto.class);
//        // Remove the films list from the ActorDto to avoid circular dependencies
////        actorDto.setFilms(null);
//        // Update the FilmActorDto objects to remove the reference to the deleted actor
////        for (FilmActorDto filmActorDto : actorDto.getFilms()) {
////            filmActorDto.setFilmActorPK(null);
////        }
//        return actorDto;
//    }



}