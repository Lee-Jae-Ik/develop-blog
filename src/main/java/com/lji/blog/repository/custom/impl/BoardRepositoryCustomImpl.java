package com.lji.blog.repository.custom.impl;

import com.lji.blog.model.schema.Board;
import com.lji.blog.model.schema.QBoard;
import com.lji.blog.model.schema.QCategory;
import com.lji.blog.repository.custom.BoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

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
    public List<Board> findBoardByCategoryId(Long categoryId) {
        return jpaQueryFactory
                .selectFrom(QBoard.board)
                .leftJoin(QBoard.board.category, QCategory.category)
                .fetchJoin()
                .where(categoryId == null ? QBoard.board.categoryId.isNotNull() : QBoard.board.categoryId.eq(categoryId))
                .fetch();
    }
}
