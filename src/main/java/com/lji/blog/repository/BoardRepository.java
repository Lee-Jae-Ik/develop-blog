package com.lji.blog.repository;

import com.lji.blog.model.schema.Board;
import com.lji.blog.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * BoardRepostiory
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-11
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
