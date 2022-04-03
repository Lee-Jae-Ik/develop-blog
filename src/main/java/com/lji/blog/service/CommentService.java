package com.lji.blog.service;

import com.lji.blog.model.dto.CommentSaveDto;
import com.lji.blog.model.schema.Comment;

/**
 * CommentService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-03
 */
public interface CommentService {

    Comment saveComment(CommentSaveDto commentSaveDto);
}
