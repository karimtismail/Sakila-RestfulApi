package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.StaffDTOReq;
import com.iti.sakilaapi.model.dto.response.StaffDTOResp;
import com.iti.sakilaapi.model.entity.Staff;

public class StaffService extends BaseService<Staff, Integer, StaffDTOReq, StaffDTOResp> {
    public StaffService() {
        super(Staff.class, StaffDTOReq.class, StaffDTOResp.class);
    }
}