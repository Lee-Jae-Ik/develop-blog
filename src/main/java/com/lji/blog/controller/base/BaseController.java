package com.lji.blog.controller.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * BaseController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public class BaseController extends BaseAbstractController{
    @Override
    protected HttpHeaders getCommonHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = StandardCharsets.UTF_8;
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, utf8));
        return httpHeaders;
    }
}
