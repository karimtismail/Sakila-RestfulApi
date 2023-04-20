package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.FilmActorDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmActorDTOResp;
import com.iti.sakilaapi.model.entity.FilmActor;

public class FilmActorService extends BaseService<FilmActor, Integer, FilmActorDTOReq, FilmActorDTOResp> {
    public FilmActorService() {
        super(FilmActor.class, FilmActorDTOReq.class, FilmActorDTOResp.class);
    }
}