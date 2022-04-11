package com.lji.blog.predicate;

import com.lji.blog.model.dto.BoardShowDto;
import com.lji.blog.model.schema.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * BoardPredicate
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-11
 */
public class BoardPredicate {


    public static Predicate searchBoard(Pageable pageable, Long categoryId) {
        QBoard board = QBoard.board;

        BooleanBuilder builder = new BooleanBuilder();

        if (!ObjectUtils.isEmpty(categoryId)) {
            builder.and(board.categoryId.eq(categoryId));
        }

        return builder;
    }
}
