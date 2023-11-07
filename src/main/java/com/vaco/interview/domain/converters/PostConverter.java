package com.vaco.interview.domain.converters;

import com.vaco.interview.controllers.dto.PostDto;
import com.vaco.interview.domain.entities.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostConverter implements Converter<Post, PostDto> {

    /**
     * Convert a Post (entity) to PostDto (Dto)
     * @param source the entity to convert
     * @return post dto object
     */
    @Override
    public PostDto convert(Post source) {
        return PostDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .contents(source.getContents())
                .timeStamp(source.getTimeStamp())
                .categoryId(source.getCategory().getId())
                .build();
    }

}
