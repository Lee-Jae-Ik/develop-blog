package com.lji.blog.controller;

import com.lji.blog.controller.base.BaseController;
import com.lji.blog.model.dto.CommentSaveDto;
import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommentController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-03
 */
@RestController
public class CommentController extends BaseController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<BlogApiResponse> saveComment(@RequestBody CommentSaveDto commentSaveDto) {
        return responseApi(commentService.saveComment(commentSaveDto));
    }
}
