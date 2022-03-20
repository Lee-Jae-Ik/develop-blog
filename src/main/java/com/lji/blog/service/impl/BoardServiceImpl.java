package com.lji.blog.service.impl;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.mapper.BoardMapper;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.dto.BoardDetailDto;
import com.lji.blog.model.dto.BoardShowDto;
import com.lji.blog.model.schema.Board;
import com.lji.blog.model.schema.Category;
import com.lji.blog.repository.BoardRepository;
import com.lji.blog.repository.CategoryRepository;
import com.lji.blog.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BoardServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final CategoryRepository categoryRepository;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper, CategoryRepository categoryRepository) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<BoardShowDto> showBoardList(Pageable pageable) {
        List<Board> boardList = boardMapper.showBoard(pageable);
        return boardList.stream().map(board ->
                BoardShowDto.builder()
                        .id(board.getId())
                        .userId(board.getUserId())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .category(categoryRepository.findById(board.getCategoryId()).orElse(null))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public BoardDetailDto showBoardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_BOARD));
        return BoardDetailDto.builder()
                .id(board.getId())
                .userId(board.getUserId())
                .title(board.getTitle())
                .contents(board.getContents())
                .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
