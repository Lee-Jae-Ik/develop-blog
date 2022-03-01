package com.lji.blog.model.response;

import lombok.Getter;

/**
 * BlogApiResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public class BlogApiResponse<T> {

    @Getter
    private BlogApiResult result;

    @Getter
    private T data;

    public BlogApiResponse() {
    }

    public BlogApiResponse(BlogApiResult apiResult, T data) {
        this.result = apiResult;
        this.data = data;
    }

    public BlogApiResponse(BlogApiResult apiResult, T data, String message) {
        this.result = apiResult;
        this.data = data;
        this.result.setMessage(message);
    }

    public int getResultCode() {
        return this.result.getResultCode();
    }

    public String getMessage() {
        return this.result.getMessage();
    }
}
