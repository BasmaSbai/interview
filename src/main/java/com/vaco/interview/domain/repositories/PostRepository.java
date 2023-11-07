package com.vaco.interview.domain.repositories;

import com.vaco.interview.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Find a list of posts based on a category id
     * @param categoryId the category id
     * @return list of posts
     */
    @Query("SELECT p FROM Post p JOIN Category c ON p.category.id = c.id WHERE c.id = :id ORDER BY p.timeStamp DESC")
    List<Post> findAllByCategoryId(@Param("id") Long categoryId);

}
