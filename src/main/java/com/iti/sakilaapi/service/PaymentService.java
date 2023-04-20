package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.PaymentDTOReq;
import com.iti.sakilaapi.model.dto.response.PaymentDTOResp;
import com.iti.sakilaapi.model.entity.Payment;

public class PaymentService extends BaseService<Payment, Integer, PaymentDTOReq, PaymentDTOResp> {
    public PaymentService() {
        super(Payment.class, PaymentDTOReq.class, PaymentDTOResp.class);
    }
}