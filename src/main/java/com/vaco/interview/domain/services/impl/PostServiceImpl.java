package com.vaco.interview.domain.services.impl;

import com.flextrade.jfixture.JFixture;
import com.vaco.interview.controllers.dto.PostDto;
import com.vaco.interview.domain.converters.PostConverter;
import com.vaco.interview.domain.entities.Category;
import com.vaco.interview.domain.entities.Post;
import com.vaco.interview.domain.exceptions.CategoryNotFoundException;
import com.vaco.interview.domain.exceptions.PostNotFoundException;
import com.vaco.interview.domain.repositories.CategoryRepository;
import com.vaco.interview.domain.repositories.PostRepository;
import com.vaco.interview.domain.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryRepository categoryRepository;
    private final PostConverter converter;

    @Override
    public List<PostDto> getPosts(Long categoryId) {
        List<Post> results;
        if (categoryId != null) {
            results = repository.findAllByCategoryId(categoryId);
        } else {
            Sort sort = Sort.by(Sort.Order.desc("timeStamp"));
            results = repository.findAll(sort);
        }
        return results
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long id) throws PostNotFoundException {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            return converter.convert(post.get());
        } else {
            throw new PostNotFoundException("Post with id " + id + " not found");
        }
    }

    @Override
    public PostDto createPost(PostDto postDto) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(postDto.getCategoryId());
        if (category.isPresent()) {
            Post post = Post.builder()
                    .title(postDto.getTitle())
                    .contents(postDto.getContents())
                    .timeStamp(LocalDateTime.now())
                    .category(category.get())
                    .build();
            return converter.convert(repository.save(post));
        } else {
            throw new CategoryNotFoundException("Category with id " + postDto.getCategoryId() + " not found");
        }
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) throws PostNotFoundException, CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(postDto.getCategoryId());
        if (category.isPresent()) {
            Optional<Post> post = repository.findById(id);
            if (post.isPresent()) {
                var postToSave = post.get();
                postToSave.setTitle(postDto.getTitle());
                postToSave.setContents(postDto.getTitle());
                postToSave.setCategory(category.get());
                return converter.convert(repository.save(postToSave));
            } else {
                throw new PostNotFoundException("Post with id " + id + " not found");
            }
        } else {
            throw new CategoryNotFoundException("Category with id " + postDto.getCategoryId() + " not found");
        }
    }

    @Override
    public void deletePosts() {
        repository.deleteAll();
    }

    @Override
    public void deletePost(Long id) throws PostNotFoundException {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            repository.delete(post.get());
        } else {
            throw new PostNotFoundException("Post with id " + id + " not found");
        }
    }

    @Override
    public void generateSampleData() {
        JFixture fixture = new JFixture();
        repository.saveAll(fixture.collections().createCollection(Post.class, 20));
    }

}
