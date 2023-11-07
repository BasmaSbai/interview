package com.vaco.interview.controllers;

import com.vaco.interview.controllers.dto.PostDto;
import com.vaco.interview.domain.exceptions.CategoryNotFoundException;
import com.vaco.interview.domain.exceptions.PostNotFoundException;
import com.vaco.interview.domain.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    /**
     * Return a list of posts
     * @param categoryId (optional) the category id
     * @return list of post dto objects
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(service.getPosts(categoryId));
    }

    /**
     * Return a post based on an id
     * @param id the post id
     * @return post dto object
     */
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getPost(id));
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new post object
     * @param postDto the post object
     * @return post dto object
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto) {
        try {
            return ResponseEntity.ok(
                    service.createPost(postDto)
            );
        } catch (CategoryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update an existing post object based on an id
     * @param id the post id to update
     * @param postDto the new post details
     * @return post dto object
     */
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody @Valid PostDto postDto) {
        try {
            return ResponseEntity.ok(service.updatePost(id, postDto));
        } catch (PostNotFoundException | CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete all posts
     * @return true
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deletePosts() {
        service.deletePosts();
        return ResponseEntity.ok(Boolean.TRUE);
    }

    /**
     * Delete a post based on an id
     * @param id the post id to delete
     * @return true
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        try {
            service.deletePost(id);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Generate sample posts data
     * @return true
     */
    @GetMapping("generateSampleData")
    public ResponseEntity<Boolean> generateSampleData() {
        service.generateSampleData();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
