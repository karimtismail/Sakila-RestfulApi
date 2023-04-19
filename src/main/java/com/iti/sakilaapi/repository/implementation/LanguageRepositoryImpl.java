package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.model.entity.Language;
import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.LanguageRepository;

public class LanguageRepositoryImpl extends BaseEntityRepositoryImpl<Language, Integer> implements LanguageRepository {
    public LanguageRepositoryImpl() {
        super(new TransactionalEntityManager(), Language.class);
    }

}
