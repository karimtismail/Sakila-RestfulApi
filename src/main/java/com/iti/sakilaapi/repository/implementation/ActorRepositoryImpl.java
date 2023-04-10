package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.entity.Actor;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.ActorRepository;

public class ActorRepositoryImpl extends BaseEntityRepositoryImpl<Actor, Short> implements ActorRepository {
    public ActorRepositoryImpl() {
        super(new TransactionalEntityManager(), Actor.class);
    }
}
