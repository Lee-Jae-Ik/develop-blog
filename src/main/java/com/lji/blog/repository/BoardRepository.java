package com.lji.blog.repository;

import com.lji.blog.model.schema.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BoardRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
