package com.lji.blog.service;

import com.lji.blog.model.dto.UserSignInDto;
import com.lji.blog.model.response.TokenResponse;
import com.lji.blog.model.schema.User;

/**
 * UserService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/02/17
 */
public interface UserService {

    TokenResponse saveUser(User user);

    TokenResponse signIn(UserSignInDto userSignInDto);
}
