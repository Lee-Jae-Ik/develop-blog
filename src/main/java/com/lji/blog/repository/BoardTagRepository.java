package com.lji.blog.repository;

import com.lji.blog.model.schema.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * BoardTagRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/04/23
 */
@Repository
@Transactional
public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {
    BoardTag findBoardTagByTagName(String tagName);
}
