package com.lji.blog.service;

import com.lji.blog.model.dto.BoardDetailDto;
import com.lji.blog.model.dto.BoardIdDto;
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
    /*게시글 등록*/
    Board saveBoard(BoardSaveDto boardSaveDto);

    /*게시글 조회 및 검색*/
    BoardListDto showBoardList(Pageable pageable, Long categoryId);
    BoardDetailDto showBoardDetail(Long id);
    BoardListDto searchBoardList(Pageable pageable, String title, String userName);

    /* 게시글 삭제 및 수정 */
    BoardIdDto deleteBoardById(Long id);
}
