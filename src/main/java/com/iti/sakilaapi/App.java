package com.iti.sakilaapi;

import com.iti.sakilaapi.model.dto.ActorDto;
import com.iti.sakilaapi.model.entity.Actor;
import com.iti.sakilaapi.repository.implementation.ActorRepositoryImpl;
import com.iti.sakilaapi.service.ActorService;

import java.util.Date;

public class App {
    public static void main(String[] args) {

//        ActorRepositoryImpl actorRepository = new ActorRepositoryImpl();
//        System.out.println(actorRepository.deleteById((short) 230));
        ActorService actorService = new ActorService(Actor.class, ActorDto.class);
        System.out.println(actorService.deleteById((short) 232));

    }

}