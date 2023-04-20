package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.CityDTOReq;
import com.iti.sakilaapi.model.dto.response.CityDTOResp;
import com.iti.sakilaapi.model.entity.City;

public class CityService extends BaseService<City, Integer, CityDTOReq, CityDTOResp> {
    public CityService() {
        super(City.class, CityDTOReq.class, CityDTOResp.class);
    }
}