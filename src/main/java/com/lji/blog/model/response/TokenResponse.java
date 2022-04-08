package com.lji.blog.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TokenResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponse {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;

    @Builder
    public TokenResponse(String ACCESS_TOKEN, String REFRESH_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
        this.REFRESH_TOKEN = REFRESH_TOKEN;
    }
}
