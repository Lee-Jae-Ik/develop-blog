package com.lji.blog.controller.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * ControllerLoggingAdvice
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Component
@Aspect
@Slf4j
public class ControllerLoggingAdvice {
    @Before("execution(* com.lji.*.controller.*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {
        final long CURRENT_THREAD_ID = Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        sb.append("\n[").append(CURRENT_THREAD_ID).append("] ================================================================================================>>");
        sb.append("\n[").append(CURRENT_THREAD_ID).append("]>>> Target:: ").append(joinPoint.getTarget().getClass().getName()).append(".").append(joinPoint.getSignature().getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        sb.append("\n[").append(CURRENT_THREAD_ID).append("]>>> URI:: [").append(request.getMethod()).append("] ").append(request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            sb.append("\n[").append(CURRENT_THREAD_ID).append("]>>> Headers[").append(headerName).append("]:: ").append(headerValue);
        }
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] paramNames = codeSignature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int bound = paramNames.length;
        IntStream.range(0, bound).mapToObj(i -> "\n[" + CURRENT_THREAD_ID + "]>>> Params[" + paramNames[i] + "]:: " + ((Optional.ofNullable(paramValues[i]).isPresent()) ? paramValues[i].toString() : null)).forEach(sb::append);
        sb.append("\n[").append(CURRENT_THREAD_ID).append("] ================================================================================================>>\n");

        log.info(sb.toString());
    }
}
