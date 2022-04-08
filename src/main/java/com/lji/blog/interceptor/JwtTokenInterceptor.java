package com.lji.blog.interceptor;

import com.lji.blog.util.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtTokenInterceptor
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final TokenUtils tokenUtils;

    public JwtTokenInterceptor(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("ACCESS_TOKEN");
        String refreshToken = request.getHeader("REFRESH_TOKEN");

        if (StringUtils.hasText(accessToken)) {
            if (tokenUtils.isValidToken(accessToken)) {
                return true;
            }
        }
        response.setStatus(404);
        response.setHeader("ACCESS_TOKEN",accessToken);
        response.setHeader("REFRESH_TOKEN",refreshToken);
        response.setHeader("msg", "Check the Tokens.");
        return false;
    }
}
