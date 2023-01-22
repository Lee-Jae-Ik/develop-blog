package com.lji.blog.aop;

import com.lji.blog.aop.annotation.BoardTag;
import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.model.response.BlogApiResult;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * BoardValidationAop
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2023/01/21
 */
@Aspect
public class BoardValidationAop implements ConstraintValidator<BoardTag, List<String>> {

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {

        if (value.size() > 10) {
            throw new BlogApiRuntimeException(BlogApiResult.OVER_BOARD_TAG_COUNT, context.getDefaultConstraintMessageTemplate());
        }

        return true;
    }
}
