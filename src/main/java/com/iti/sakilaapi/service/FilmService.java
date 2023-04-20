package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.FilmDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmDTOResp;
import com.iti.sakilaapi.model.entity.Film;

public class FilmService extends BaseService<Film, Integer, FilmDTOReq, FilmDTOResp> {
    public FilmService() {
        super(Film.class, FilmDTOReq.class, FilmDTOResp.class);
    }
}