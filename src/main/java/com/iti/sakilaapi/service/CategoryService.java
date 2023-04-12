package com.iti.sakilaapi.service;

import com.iti.sakilaapi.presentation.dto.CategoryDto;
import com.iti.sakilaapi.data.entity.Category;
import com.iti.sakilaapi.data.repository.implementation.CategoryRepositoryImpl;
import com.iti.sakilaapi.data.repository.interfaces.CategoryRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ActorService instance with a default ActorRepository implementation and a new ModelMapper instance.
     */
    public CategoryService() {
        this.categoryRepository = new CategoryRepositoryImpl();
        this.modelMapper = new ModelMapper();
    }

    public CategoryDto findById(Short categoryId) {
        Category category = categoryRepository.findById(categoryId);
        return modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(modelMapper.map(category, CategoryDto.class));
        }
        return categoryDtos;
    }

    public CategoryDto save(Category category) {
        Category savedCateogry = categoryRepository.save(category);
        return modelMapper.map(savedCateogry, CategoryDto.class);
    }

    public CategoryDto update(Category category) {
        Category updatedCateogry = categoryRepository.update(category);
        return modelMapper.map(updatedCateogry, CategoryDto.class);
    }

    public CategoryDto deleteById(Short categoryId) {
        Category deleteCateogry = categoryRepository.deleteById(categoryId);
        return modelMapper.map(deleteCateogry, CategoryDto.class);
    }
}
