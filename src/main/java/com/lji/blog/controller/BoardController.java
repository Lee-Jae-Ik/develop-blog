package com.lji.blog.controller;

import com.lji.blog.controller.base.BaseController;
import com.lji.blog.model.response.BlogApiResponse;
import com.lji.blog.model.schema.Board;
import com.lji.blog.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * BoardContorller
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@RestController
public class BoardController extends BaseController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public ResponseEntity<BlogApiResponse> saveBoard(@RequestBody Board board) {
        return responseApi(boardService.saveBoard(board));
    }

    @GetMapping("/board")
    public ResponseEntity<BlogApiResponse> showBoardList(@PageableDefault(size = 10) Pageable pageable) {
        return responseApi(boardService.showBoardList(pageable));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BlogApiResponse> showBoardDetail(@PathVariable Long id) {
        return responseApi(boardService.showBoardDetail(id));
    }
}
