package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Payment;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.PaymentRepository;

public class PaymentRepositoryImpl extends BaseEntityRepositoryImpl<Payment, Short> implements PaymentRepository {
    public PaymentRepositoryImpl() {
        super(new TransactionalEntityManager(), Payment.class);
    }

}
