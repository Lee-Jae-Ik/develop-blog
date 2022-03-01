package com.lji.blog.model.response;

/**
 * BlogSuccessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public class BlogSuccessResponse<T> extends BlogApiResponse{
    public BlogSuccessResponse(T data) {
        super(BlogApiResult.SUCCESS, data);
    }
}
