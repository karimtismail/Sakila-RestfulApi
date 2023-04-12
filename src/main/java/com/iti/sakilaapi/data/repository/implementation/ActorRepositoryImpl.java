package com.iti.sakilaapi.data.repository.implementation;

import com.iti.sakilaapi.data.entity.Actor;
import com.iti.sakilaapi.data.repository.TransactionalEntityManager;
import com.iti.sakilaapi.data.repository.interfaces.ActorRepository;

public class ActorRepositoryImpl extends BaseEntityRepositoryImpl<Actor, Short> implements ActorRepository {
    public ActorRepositoryImpl() {
        super(new TransactionalEntityManager(), Actor.class);
    }
}
