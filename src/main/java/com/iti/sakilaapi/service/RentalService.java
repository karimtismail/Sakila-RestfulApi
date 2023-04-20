package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.RentalDTOReq;
import com.iti.sakilaapi.model.dto.response.RentalDTOResp;
import com.iti.sakilaapi.model.entity.Rental;

public class RentalService extends BaseService<Rental, Integer, RentalDTOReq, RentalDTOResp> {
    public RentalService() {
        super(Rental.class, RentalDTOReq.class, RentalDTOResp.class);
    }
}