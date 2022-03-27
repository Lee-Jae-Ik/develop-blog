package com.lji.blog.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * BoardSaveDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/20
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSaveDto {

    private Long userid;
    private String title;
    private String contents;
    private Long categoryId;

    public BoardSaveDto(Long userid, String title, String contents, Long categoryId) {
        this.userid = userid;
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
    }
}
