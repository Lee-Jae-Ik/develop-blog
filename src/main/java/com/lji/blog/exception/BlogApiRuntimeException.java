package com.lji.blog.exception;

import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.model.response.BlogApiResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * BlogApiRuntimeException
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Slf4j
public class BlogApiRuntimeException extends RuntimeException{
    @Getter
    private BlogApiResult apiResult;

    public BlogApiRuntimeException() {
        super();
        apiResult = BlogApiResult.SERVER_ERROR;
    }

    public BlogApiRuntimeException(BlogApiResult apiResult) {
        super(apiResult.getMessage());
        this.apiResult = apiResult;
    }

    public BlogApiRuntimeException(BlogApiResult apiResult, String message) {
        super(message);
        apiResult.setMessage(message);
        this.apiResult = apiResult;
    }

    public BlogApiRuntimeException(String message){
        this(BlogApiResult.SERVER_ERROR,message);
    }

    public BlogApiRuntimeException(String message, Throwable cause) {
        super(message,cause);
        log.error("RuntimeException : {}", cause);
        apiResult = BlogApiResult.SERVER_ERROR;
    }

    public BlogApiRuntimeException(Throwable cause) {
        super(cause);
        log.error("RuntimeException : {}", cause);
        apiResult = BlogApiResult.SERVER_ERROR;
    }

    public BlogApiRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("RuntimeException : {}" , cause);
        apiResult = BlogApiResult.SERVER_ERROR;
    }

    protected void setApiResult(BlogApiResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
