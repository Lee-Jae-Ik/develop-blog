package com.lji.blog.controller;

import com.lji.blog.controller.base.BaseController;
import com.lji.blog.model.dto.CategorySaveDto;
import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryContorller
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/19
 */
@RestController
public class CategoryContorller extends BaseController {

    private final CategoryService categoryService;

    public CategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/category")
    public ResponseEntity<BlogApiResponse> saveCategory(@RequestBody CategorySaveDto categorySaveDto) {
        return responseApi(categoryService.saveCategory(categorySaveDto));
    }
}
