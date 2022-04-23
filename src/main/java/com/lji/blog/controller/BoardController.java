package com.lji.blog.controller;

import com.lji.blog.controller.base.BaseController;
import com.lji.blog.model.dto.BoardSaveDto;
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
    public ResponseEntity<BlogApiResponse> saveBoard(@RequestBody BoardSaveDto boardSaveDto) {
        return responseApi(boardService.saveBoard(boardSaveDto));
    }

    @GetMapping("/board")
    public ResponseEntity<BlogApiResponse> showBoardList(@PageableDefault(size = 10) Pageable pageable,
                                                         @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return responseApi(boardService.showBoardList(pageable,categoryId));
    }
    @GetMapping("/board/search")
    public ResponseEntity<BlogApiResponse> searchBoard(@RequestParam(value = "title", required = false) String title,
                                                       @RequestParam(value = "userName", required = false) String userName,
                                                       @PageableDefault(size = 10) Pageable pageable) {
        return responseApi(boardService.searchBoardList(pageable,title,userName));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BlogApiResponse> showBoardDetail(@PathVariable Long id) {
        return responseApi(boardService.showBoardDetail(id));
    }

    @PostMapping("/board/delete/{id}")
    public ResponseEntity<BlogApiResponse> deleteBoard(@RequestParam Long id) {
        return responseApi(boardService.deleteBoardById(id));
    }
}
