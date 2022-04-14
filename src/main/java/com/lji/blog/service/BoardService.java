package com.lji.blog.service;

import com.lji.blog.model.dto.BoardDetailDto;
import com.lji.blog.model.dto.BoardListDto;
import com.lji.blog.model.dto.BoardSaveDto;
import com.lji.blog.model.schema.Board;
import org.springframework.data.domain.Pageable;

/**
 * BoardService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
public interface BoardService {
    Board saveBoard(BoardSaveDto boardSaveDto);
    BoardListDto showBoardList(Pageable pageable, Long categoryId);
    BoardDetailDto showBoardDetail(Long id);
}
