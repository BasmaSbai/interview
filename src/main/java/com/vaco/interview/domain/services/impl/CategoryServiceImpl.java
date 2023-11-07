package com.vaco.interview.domain.services.impl;

import com.vaco.interview.controllers.dto.CategoryDto;
import com.vaco.interview.domain.converters.CategoryConverter;
import com.vaco.interview.domain.repositories.CategoryRepository;
import com.vaco.interview.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryConverter converter;

    @Override
    public List<CategoryDto> getCategories() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
