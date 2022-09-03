package com.lji.blog.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * BoardIdDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/04/23
 */
@Getter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class BoardIdDto {
    private Long id;

    @Builder
    public BoardIdDto(Long id) {
        this.id = id;
    }
}
