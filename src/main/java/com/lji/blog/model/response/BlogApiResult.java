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

    SERVER_ERROR(-1, "SERVER ERROR"),
    ALREADY_HAVE_CATEGORY(9001, "해당 카테고리는 이미 존재 합니다."),
    NOT_HAVE_BOARD(9002, "해당 게시글은 존재하지 않습니다."),
    NOT_HAVE_CATEGORY(9003, "해당 카테고리는 없습니다."),
    NOT_HAVE_USER(9002, "해당 유저가 없습니다."),
    OVER_BOARD_TAG_COUNT(9000, "게시글 태그는 최대 10개 까지 등록이 가능합니다."),
    WRONG_DATA(8000, "잘못된 데이터 형식 입니다.")
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
