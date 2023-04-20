package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.InventoryDTOReq;
import com.iti.sakilaapi.model.dto.response.InventoryDTOResp;
import com.iti.sakilaapi.model.entity.Inventory;

public class InventoryService extends BaseService<Inventory, Integer, InventoryDTOReq, InventoryDTOResp> {
    public InventoryService() {
        super(Inventory.class, InventoryDTOReq.class, InventoryDTOResp.class);
    }
}