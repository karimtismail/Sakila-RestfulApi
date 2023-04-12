package com.iti.sakilaapi.service;

import com.iti.sakilaapi.presentation.dto.ActorDto;
import com.iti.sakilaapi.data.entity.Actor;
import com.iti.sakilaapi.data.repository.implementation.ActorRepositoryImpl;
import com.iti.sakilaapi.data.repository.interfaces.ActorRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing actors. Provides methods for retrieving, creating, updating, and deleting actors.
 */
public class ActorService {

    private final ActorRepository actorRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public ActorService() {
        this.actorRepository = new ActorRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    /**
     * Retrieves the actor with the given ID.
     *
     * @param actorId the ID of the actor to retrieve
     * @return the ActorDto corresponding to the retrieved actor, or null if no actor with the given ID was found
     */
    public ActorDto findById(Short actorId) {
        Actor actor = actorRepository.findById(actorId);
        return modelMapper.map(actor, ActorDto.class);
    }

    /**
     * Retrieves all actors.
     *
     * @return a list of ActorDtos corresponding to the retrieved actors
     */
    public List<ActorDto> findAll() {
        List<Actor> actors = actorRepository.findAll();
        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) {
            actorDtos.add(modelMapper.map(actor, ActorDto.class));
        }
        return actorDtos;
    }

    /**
     * Saves the given actor to the database.
     *
     * @param actor the actor to save
     * @return the ActorDto corresponding to the saved actor
     */
    public ActorDto save(Actor actor) {
        Actor savedActor = actorRepository.save(actor);
        return modelMapper.map(savedActor, ActorDto.class);
    }

    /**
     * Updates the given actor in the database.
     *
     * @param actor the actor to update
     * @return the ActorDto corresponding to the updated actor
     */
    public ActorDto update(Actor actor) {
        Actor updatedActor = actorRepository.update(actor);
        return modelMapper.map(updatedActor, ActorDto.class);
    }

    /**
     * Deletes the actor with the given ID from the database.
     *
     * @param actorId the ID of the actor to delete
     * @return the ActorDto corresponding to the deleted actor, or null if no actor with the given ID was found
     */
    public ActorDto deleteById(Short actorId) {
        Actor deleteActor = actorRepository.deleteById(actorId);
        return modelMapper.map(deleteActor, ActorDto.class);
    }
}