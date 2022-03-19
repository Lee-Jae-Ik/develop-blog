package com.lji.blog.service;

import com.lji.blog.model.dto.CategoryIdDto;
import com.lji.blog.model.dto.CategorySaveDto;

/**
 * CategoryService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-03-19
 */
public interface CategoryService {
    CategoryIdDto saveCategory(CategorySaveDto categorySaveDto);
}
