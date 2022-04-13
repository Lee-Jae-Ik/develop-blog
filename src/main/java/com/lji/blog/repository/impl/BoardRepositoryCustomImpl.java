package com.lji.blog.repository.impl;

import com.lji.blog.model.schema.Board;
import com.lji.blog.model.schema.QBoard;
import com.lji.blog.repository.BoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BoardRepositoryImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-11
 */
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {


    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Board> findBoardByCategoryId(Pageable pageable, Long categoryId) {
        return jpaQueryFactory
                .selectFrom(QBoard.board)
                .where(categoryId == null ? QBoard.board.categoryId.isNotNull() : QBoard.board.categoryId.eq(categoryId))
                .fetch();
    }
}
