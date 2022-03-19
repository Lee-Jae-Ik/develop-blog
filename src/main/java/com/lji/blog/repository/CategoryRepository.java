package com.lji.blog.repository;

import com.lji.blog.model.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-03-19
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByIdAndCategoryName(Long id, String categoryName);
}
