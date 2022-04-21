package com.lji.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lji.blog.model.schema.Category;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * BoardVo
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Getter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardShowDto {

    private Long id;
    private String userName;
    private String title;
    private String createdDate;
    private String modifiedDate;
    private Category category;
    private int commentCount;

    @Builder
    public BoardShowDto(Long id, String userName, String title, String createdDate, String modifiedDate, Category category, int commentCount) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.category = category;
        this.commentCount = commentCount;
    }
}
