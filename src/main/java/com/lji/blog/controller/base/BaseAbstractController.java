package com.lji.blog.controller.base;

import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.response.BlogErrorResponse;
import com.lji.blog.model.response.BlogSuccessResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BaseAbstractController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public abstract class BaseAbstractController {

    protected <T> ResponseEntity<BlogApiResponse> responseApi(T body) {
        BlogApiResponse response = new BlogSuccessResponse(body);
        return response(response);
    }

    protected <T> ResponseEntity<BlogErrorResponse> responseErrorApi(BlogApiResult result) {
        BlogErrorResponse response = new BlogErrorResponse(result);
        return responseError(response);
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    private <T> ResponseEntity<T> responseError(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
