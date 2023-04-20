package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.CustomerDTOReq;
import com.iti.sakilaapi.model.dto.response.CustomerDTOResp;
import com.iti.sakilaapi.model.entity.Customer;

public class CustomerService extends BaseService<Customer, Integer, CustomerDTOReq, CustomerDTOResp> {
    public CustomerService() {
        super(Customer.class, CustomerDTOReq.class, CustomerDTOResp.class);
    }
}