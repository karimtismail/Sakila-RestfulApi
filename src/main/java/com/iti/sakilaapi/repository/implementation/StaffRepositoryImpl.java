package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Staff;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.StaffRepository;

public class StaffRepositoryImpl extends BaseEntityRepositoryImpl<Staff, Integer> implements StaffRepository {
    public StaffRepositoryImpl() {
        super(new TransactionalEntityManager(), Staff.class);
    }

}
