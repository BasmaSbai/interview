package com.vaco.interview.domain.repositories;

import com.vaco.interview.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
