package com.lji.blog.service.impl;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.model.dto.CategoryIdDto;
import com.lji.blog.model.dto.CategorySaveDto;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.schema.Category;
import com.lji.blog.repository.CategoryRepository;
import com.lji.blog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * CategoryServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-03-19
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryIdDto saveCategory(CategorySaveDto categorySaveDto) {
        Category findCategory = Optional.ofNullable(categoryRepository.
                findCategoryByIdAndCategoryName(categorySaveDto.getId(), categorySaveDto.getCategoryName()))
                .orElseGet(Category::new);

        Category insertCategory = Category.builder()
                .id(findCategory.getId())
                .categoryName(categorySaveDto.getCategoryName())
                .postCount(findCategory.getPostCount())
                .build();
        categoryRepository.save(insertCategory);

        return CategoryIdDto.builder().id(insertCategory.getId()).build();
    }
}
