package com.lji.blog.service;

import com.lji.blog.model.schema.User;
import org.springframework.http.ResponseEntity;

/**
 * UserService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/02/17
 */
public interface UserService {

    User saveUser(User user);
}
