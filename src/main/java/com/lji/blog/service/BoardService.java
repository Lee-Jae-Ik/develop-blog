package com.lji.blog.service;

import com.lji.blog.model.vo.BoardDetailVo;
import com.lji.blog.model.vo.BoardVo;
import com.lji.blog.model.schema.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * BoardService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public interface BoardService {
    Board saveBoard(Board board);
    List<BoardVo> showBoardList(Pageable pageable);
    BoardDetailVo showBoardDetail(Long id);
}
