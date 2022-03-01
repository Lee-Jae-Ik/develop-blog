package com.lji.blog.model.response;

import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * BlogErrorResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public class BlogErrorResponse<T> extends BlogApiResponse{

    @Setter
    private String changeMessage;

    public BlogErrorResponse(BlogApiResult blogApiResult) {
        super(blogApiResult, null);
    }

    public BlogErrorResponse(BlogApiResult blogApiResult, T data) {
        super(blogApiResult, data);
    }

    @Override
    public String getMessage() {
        if (!StringUtils.hasText(this.changeMessage)) {
            return super.getMessage();
        } else {
            return this.changeMessage;
        }
    }
}
