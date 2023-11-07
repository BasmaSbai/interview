package com.vaco.interview.domain.services;

import com.vaco.interview.controllers.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * Get a list of all categories
     * @return list of category dto objects
     */
    List<CategoryDto> getCategories();

}
