package com.lji.blog.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BoardTag
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2023/01/21
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BoardTag {
    String message() default "게시글 태그는 10개 까지 가능합니다.";
}
