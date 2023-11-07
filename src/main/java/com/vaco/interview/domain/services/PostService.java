package com.vaco.interview.domain.services;

import com.vaco.interview.controllers.dto.PostDto;
import com.vaco.interview.domain.exceptions.CategoryNotFoundException;
import com.vaco.interview.domain.exceptions.PostNotFoundException;

import java.util.List;

public interface PostService {

    /**
     * Get a list of posts based on category id (or not)
     * @param categoryId (optional) a category id
     * @return a list of post dto objects
     */
    List<PostDto> getPosts(Long categoryId);

    /**
     * Get a post based on an id
     * @param id the post id
     * @return post dto object
     * @throws PostNotFoundException
     */
    PostDto getPost(Long id) throws PostNotFoundException;

    /**
     * create a new post object
     * @return post dto object
     * @throws CategoryNotFoundException
     */
    PostDto createPost(PostDto postDto) throws CategoryNotFoundException;

    /**
     * update an existing post object based on an id
     * @param id the post id to update
     * @param postDto the new post details
     * @return post dto object
     * @throws PostNotFoundException
     * @throws CategoryNotFoundException
     */
    PostDto updatePost(Long id, PostDto postDto) throws PostNotFoundException, CategoryNotFoundException;

    /**
     * Delete all posts
     */
    void deletePosts();

    /**
     * Delete a post based on an id
     * @param id the post id
     * @throws PostNotFoundException
     */
    void deletePost(Long id) throws PostNotFoundException;

    /**
     * Generate sample data
     */
    void generateSampleData();

}
