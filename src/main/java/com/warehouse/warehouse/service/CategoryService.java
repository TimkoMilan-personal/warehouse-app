package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void addNew(CategoryCreateDto categoryCreateDto);
}