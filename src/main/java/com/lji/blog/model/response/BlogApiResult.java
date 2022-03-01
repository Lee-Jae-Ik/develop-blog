package com.lji.blog.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * BlogApiResult
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public enum BlogApiResult {

    NONE(0, "NONE"),
    SUCCESS(200, "SUCCESS"),
    SERVER_ERROR(-1, "SERVER ERROR")
    ;

    @Getter
    private final int resultCode;

    @Getter
    @Setter
    private String message;

    @Getter
    private String defaultMessage;

    BlogApiResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    BlogApiResult(int resultCode) {
        this.resultCode = resultCode;
        this.message = searchByResultCode(resultCode).getMessage();
    }

    public static final Map<Integer, BlogApiResult> BlogApiResultMap = new HashMap<>();
    static {
        for (BlogApiResult blogApiResult : BlogApiResult.values()) {
            BlogApiResultMap.put(blogApiResult.getResultCode(), blogApiResult);
        }
    }

    public void initMessage() {
        this.message = this.defaultMessage;
    }

    private static BlogApiResult searchByResultCode(int resultCode) {
        return BlogApiResultMap.get(resultCode);
    }
}
