package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.LanguageDTOReq;
import com.iti.sakilaapi.model.dto.response.LanguageDTOResp;
import com.iti.sakilaapi.model.entity.Language;

public class LanguageService extends BaseService<Language, Integer, LanguageDTOReq, LanguageDTOResp> {
    public LanguageService() {
        super(Language.class, LanguageDTOReq.class, LanguageDTOResp.class);
    }
}