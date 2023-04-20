package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.CountryDTOReq;
import com.iti.sakilaapi.model.dto.response.CountryDTOResp;
import com.iti.sakilaapi.model.entity.Country;

public class CountryService extends BaseService<Country, Integer, CountryDTOReq, CountryDTOResp> {
    public CountryService() {
        super(Country.class, CountryDTOReq.class, CountryDTOResp.class);
    }
}