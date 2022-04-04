package com.lji.blog.service.impl;

import com.lji.blog.model.dto.CommentSaveDto;
import com.lji.blog.model.schema.Comment;
import com.lji.blog.repository.CommentRepository;
import com.lji.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * CommentServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-03
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(CommentSaveDto commentSaveDto) {
        Comment insertComment = Comment.builder()
                .userName(commentSaveDto.getUserName())
                .commentContents(commentSaveDto.getCommentContents())
                .createdDate(LocalDateTime.now())
                .boardId(commentSaveDto.getBoardId())
                .build();
        commentRepository.save(insertComment);
        return insertComment;
    }
}
