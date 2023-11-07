package com.vaco.interview.domain.converters;

import com.flextrade.jfixture.JFixture;
import com.vaco.interview.controllers.dto.PostDto;
import com.vaco.interview.domain.entities.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostConverterTest {

    private PostConverter converter;

    private JFixture fixture;

    @BeforeEach
    void setUp() {
        fixture = new JFixture();
        converter = new PostConverter();
    }

    @Test
    void convert() {
        // Given
        Post given = fixture.create(Post.class);

        // When
        PostDto actual = converter.convert(given);

        // Then
        assertEquals(actual.getId(), given.getId());
        assertEquals(actual.getTimeStamp(), given.getTimeStamp());
        assertEquals(actual.getTitle(), given.getTitle());
        assertEquals(actual.getCategoryId(), given.getCategory().getId());
    }
}