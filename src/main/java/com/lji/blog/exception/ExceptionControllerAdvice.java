package com.lji.blog.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.response.BlogErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * ExceptionControllerAdvice
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BlogApiRuntimeException.class)
    public ResponseEntity<BlogApiResponse> handleOntactApiRuntimeException(BlogApiRuntimeException e) {
        log.error("OntactApiRuntimeException. => {}", e.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = StandardCharsets.UTF_8;
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, utf8));

        BlogApiResponse response = new BlogErrorResponse(e.getApiResult());
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
    }

    @ExceptionHandler({ InvalidKeyException.class, NoSuchAlgorithmException.class })
    public ResponseEntity<?> handleHashException(Exception e) {
        return systemErrorResponse(BlogApiResult.SERVER_ERROR, new Exception("Encrypt/Decrypt key is requested"), HttpStatus.LOCKED);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleAnyException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return logicalErrorResponse(BlogApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<?> handleRunTimeException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return logicalErrorResponse(BlogApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    @ExceptionHandler({ IOException.class, ParseException.class,  JsonParseException.class })
    public ResponseEntity<?> handleParseException(Exception e, HttpServletResponse response) {
        return logicalErrorResponse(BlogApiResult.SERVER_ERROR, e, HttpStatus.BAD_REQUEST,response);
    }

    @ExceptionHandler({InterruptedException.class})
    public ResponseEntity<?> handleInterruptedException(Exception e, HttpServletResponse response){
        e.printStackTrace();
        return logicalErrorResponse(BlogApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    private ResponseEntity<BlogErrorResponse> systemErrorResponse(BlogApiResult result, Exception e, HttpStatus status) {
        String message = getMessage(e);
        BlogErrorResponse<String> errorResponse = new BlogErrorResponse<>(result, message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**
     * @param result
     * @param e
     * @param status
     * @param response
     * @return
     */
    private ResponseEntity<?> logicalErrorResponse(BlogApiResult result, Exception e, HttpStatus status, HttpServletResponse response) {
        String message = getMessage(e);
        response.setHeader("errorMessageCode", String.valueOf(result.getResultCode()));
        response.setHeader("errorMessage", result.getMessage());

        BlogErrorResponse<String> errorResponse = new BlogErrorResponse<>(result,message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).header("errorMessage",message).body(errorResponse);
    }

    private String getMessage(Exception e) {
        String message;
        if (ObjectUtils.isEmpty(e)) {
            message = "unknown error occurred";
        } else {
            message = e.toString();
        }

        return message;
    }
}
