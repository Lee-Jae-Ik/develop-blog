package com.lji.blog.exception;

import com.lji.blog.model.response.BlogApiResult;
import lombok.extern.slf4j.Slf4j;

/**
 * InvalidRequestParameterException
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Slf4j
public class InvalidRequestParameterException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "InvalidParam";
    private static final String MESSAGE_PREFIX = DEFAULT_MESSAGE + ": ";

    private BlogApiResult result;

    public InvalidRequestParameterException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidRequestParameterException(String message) {
        super(MESSAGE_PREFIX + message);
    }

    public InvalidRequestParameterException(String message, Throwable cause) {
        super(MESSAGE_PREFIX + message, cause);
    }

    public InvalidRequestParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(MESSAGE_PREFIX + message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidRequestParameterException(BlogApiResult result, String detailMessage) {
        super(detailMessage);
        log.error("message : {}", detailMessage);
        log.error("EscrowApiResultMessage : {}", result.getMessage());
        log.error("EscrowApiResultCode : {}", result.getResultCode());
        this.result = result;
    }

    //메시지 치환방식에 사용
    public InvalidRequestParameterException(BlogApiResult result, String detailMessage, String arg) {
        super(detailMessage.replace("%n", arg));
        result.setMessage(detailMessage.replace("%n", arg));
        log.error("message : {}", detailMessage.replace("%n", arg));
        log.error("EscrowApiResultMessage : {}", result.getMessage());
        log.error("EscrowApiResultCode : {}", result.getResultCode());

        this.result = result;
    }

    public InvalidRequestParameterException(BlogApiResult result) {
        super(MESSAGE_PREFIX + result.getMessage());
        this.result = result;
    }

    public BlogApiResult getEscrowApiResult() {
        return result;
    }
}

