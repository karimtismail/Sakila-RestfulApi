package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.FilmCategoryDTOReq;
import com.iti.sakilaapi.model.dto.response.FilmCategoryDTOResp;
import com.iti.sakilaapi.model.entity.FilmCategory;

public class FilmCategoryService extends BaseService<FilmCategory, Integer, FilmCategoryDTOReq, FilmCategoryDTOResp> {
    public FilmCategoryService() {
        super(FilmCategory.class, FilmCategoryDTOReq.class, FilmCategoryDTOResp.class);
    }
}