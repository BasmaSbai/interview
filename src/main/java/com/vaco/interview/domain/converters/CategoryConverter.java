package com.vaco.interview.domain.converters;

import com.vaco.interview.controllers.dto.CategoryDto;
import com.vaco.interview.domain.entities.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryDto> {

    /**
     * Convert a Category (entity) to CategoryDto (Dto)
     * @param source the entity to convert
     * @return category dto object
     */
    @Override
    public CategoryDto convert(Category source) {
        return CategoryDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }

}