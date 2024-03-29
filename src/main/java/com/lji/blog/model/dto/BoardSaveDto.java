package com.lji.blog.model.dto;

import com.lji.blog.aop.annotation.BoardTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * BoardSaveDto
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
public class BoardSaveDto {

    @NotNull
    private Long userId;
    @NotNull
    private String title;
    @NotNull
    private String contents;
    private Long categoryId;

    @BoardTag
    private List<String> boradTagNameList;

    @Builder
    public BoardSaveDto(Long userId, String title, String contents, Long categoryId, List<String> boradTagNameList) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
        this.boradTagNameList = boradTagNameList;
    }
}
