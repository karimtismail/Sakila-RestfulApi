package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.FilmTextDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmTextDTOResp;
import com.iti.sakilaapi.model.entity.FilmText;

public class FilmTextService extends BaseService<FilmText, Integer, FilmTextDTOReq, FilmTextDTOResp> {
    public FilmTextService() {
        super(FilmText.class, FilmTextDTOReq.class, FilmTextDTOResp.class);
    }
}