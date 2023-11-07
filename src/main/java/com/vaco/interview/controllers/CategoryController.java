package com.vaco.interview.controllers;

import com.vaco.interview.controllers.dto.CategoryDto;
import com.vaco.interview.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    /**
     * Return a list of categories
     * @return ResponseEntity<List<CategoryDto>>
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }

}
