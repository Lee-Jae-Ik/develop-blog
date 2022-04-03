package com.lji.blog.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * CommentSaveDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-03
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveDto {

    private Long boardId;
    private String userName;
    private String commentContents;

    @Builder
    public CommentSaveDto(Long boardId, String userName, String commentContents) {
        this.boardId = boardId;
        this.userName = userName;
        this.commentContents = commentContents;
    }
}
