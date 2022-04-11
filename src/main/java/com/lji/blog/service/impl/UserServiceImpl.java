package com.lji.blog.service.impl;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.model.dto.UserSignInDto;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.response.TokenResponse;
import com.lji.blog.model.schema.Auth;
import com.lji.blog.model.schema.User;
import com.lji.blog.repository.AuthRepository;
import com.lji.blog.repository.UserRepository;
import com.lji.blog.service.UserService;
import com.lji.blog.util.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final TokenUtils tokenUtils;

    public UserServiceImpl(UserRepository userRepository, AuthRepository authRepository, TokenUtils tokenUtils) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public TokenResponse saveUser(User user) {
        userRepository.save(user);
        String accessToken = tokenUtils.generateJwtToken(user);
        String refreshToken = tokenUtils.saveRefreshToken(user);

        authRepository.save(Auth.builder()
                        .user(user)
                        .refreshToken(refreshToken)
                .build());

        return TokenResponse.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }

    @Override
    public TokenResponse signIn(UserSignInDto userSignInDto) {

        User findUser = Optional.ofNullable(userRepository.findUserByUserIdAndUserPassword(userSignInDto.getUserId(), userSignInDto.getUserPassword()))
                .orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER));

        Auth findAuth = Optional.ofNullable(authRepository.findAuthByUser(findUser.getId()))
                .orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER));

        String accessToken;
        String refreshToken = findAuth.getRefreshToken();

        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(findUser);
            return TokenResponse.builder()
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(refreshToken)
                    .build();
        } else {
            accessToken = tokenUtils.generateJwtToken(findUser);
            refreshToken  = tokenUtils.saveRefreshToken(findUser);
            findAuth.refreshUpdate(refreshToken);
        }

        return TokenResponse.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }
}
