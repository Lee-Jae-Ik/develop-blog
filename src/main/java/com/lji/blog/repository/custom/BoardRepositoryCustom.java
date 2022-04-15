package com.lji.blog.repository.custom;

import com.lji.blog.model.schema.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoardRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public interface BoardRepositoryCustom {
    List<Board> findBoardByCategoryId(Pageable pageable,Long categoryId);
    List<Board> searchBoardByTitleOrUserName(String title, String userName, Pageable pageable);
}
