package com.iti.sakilaapi.service;

import com.iti.sakilaapi.model.dto.requests.CategoryDTOReq;
import com.iti.sakilaapi.model.dto.response.CategoryDTOResp;
import com.iti.sakilaapi.model.entity.Category;

public class CategoryService extends BaseService<Category, Integer, CategoryDTOReq, CategoryDTOResp> {
    public CategoryService() {
        super(Category.class, CategoryDTOReq.class, CategoryDTOResp.class);
    }
}