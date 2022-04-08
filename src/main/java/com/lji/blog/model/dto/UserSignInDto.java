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
 * USerSignInDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignInDto {

    private String userId;

    private String userPassword;

    @Builder
    public UserSignInDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
