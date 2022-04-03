package com.lji.blog.mapper;

import com.lji.blog.model.schema.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoardMapper
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Mapper
@Repository
public interface BoardMapper {
    List<Board> showBoard(Pageable pageable, Long categoryId);
}
