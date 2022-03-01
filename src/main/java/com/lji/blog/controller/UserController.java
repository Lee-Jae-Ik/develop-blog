package com.lji.blog.controller;

import com.lji.blog.controller.base.BaseController;
import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.model.schema.User;
import com.lji.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/02/17
 */
@RestController
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<BlogApiResponse> saveUser(@RequestBody User user) {
        return responseApi(userService.saveUser(user));
    }
}
