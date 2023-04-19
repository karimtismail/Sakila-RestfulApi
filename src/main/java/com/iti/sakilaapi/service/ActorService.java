package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.ActorDTOReq;
import com.iti.sakilaapi.model.dto.response.ActorDTOResp;
import com.iti.sakilaapi.model.entity.Actor;
import com.iti.sakilaapi.repository.interfaces.ActorRepository;
import jakarta.inject.Inject;

public class ActorService extends BaseService<Actor, Integer, ActorDTOReq, ActorDTOResp> {

    public ActorService() {
        super(Actor.class, ActorDTOReq.class, ActorDTOResp.class);
    }
}