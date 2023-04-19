package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.AddressDTOReq;
import com.iti.sakilaapi.model.dto.response.AddressDTOResp;
import com.iti.sakilaapi.model.entity.Address;

public class AddressService extends BaseService<Address, Integer, AddressDTOReq, AddressDTOResp> {

    public AddressService() {
        super(Address.class, AddressDTOReq.class, AddressDTOResp.class);
    }
}