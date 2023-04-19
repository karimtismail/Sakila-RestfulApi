package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.StoreDTOReq;
import com.iti.sakilaapi.model.dto.response.StoreDTOResp;
import com.iti.sakilaapi.model.entity.Store;

public class StoreService extends BaseService<Store, Integer, StoreDTOReq, StoreDTOResp> {

    public StoreService() {
        super(Store.class, StoreDTOReq.class, StoreDTOResp.class);
    }
}