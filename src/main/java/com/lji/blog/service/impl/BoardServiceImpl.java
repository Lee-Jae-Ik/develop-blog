package com.lji.blog.service.impl;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.mapper.BoardMapper;
import com.lji.blog.model.dto.BoardSaveDto;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.dto.BoardDetailDto;
import com.lji.blog.model.dto.BoardShowDto;
import com.lji.blog.model.schema.Board;
import com.lji.blog.model.schema.Category;
import com.lji.blog.model.schema.Comment;
import com.lji.blog.repository.BoardRepository;
import com.lji.blog.repository.CategoryRepository;
import com.lji.blog.repository.CommentRepository;
import com.lji.blog.repository.UserRepository;
import com.lji.blog.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper, CategoryRepository categoryRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Board saveBoard(BoardSaveDto boardSaveDto) {

        Category findCategory = categoryRepository.findById(boardSaveDto.getCategoryId())
                .orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_CATEGORY));

        Board insertBoard = Board.builder()
                .userId(boardSaveDto.getUserId())
                .categoryId(boardSaveDto.getCategoryId())
                .title(boardSaveDto.getTitle())
                .contents(boardSaveDto.getContents())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .category(findCategory)
                .commentList(null)
                .build();

        findCategory.setPostCount(findCategory.getPostCount() + 1);
        boardRepository.save(insertBoard);

        return insertBoard;
    }

    @Override
    public List<BoardShowDto> showBoardList(Pageable pageable, Long categoryId) {
        List<Board> boardList = boardMapper.showBoard(pageable,categoryId);
        return boardList.stream().map(board ->
                BoardShowDto.builder()
                        .id(board.getId())
                        .userName(userRepository.findById(board.getUserId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER)).getUserName())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .category(categoryRepository.findById(board.getCategoryId()).orElse(null))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public BoardDetailDto showBoardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_BOARD));
        Category category = categoryRepository.findById(board.getCategoryId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_CATEGORY));
        List<Comment> commentList = commentRepository.findCommentsByBoardId(board.getId());

        return BoardDetailDto.builder()
                .id(board.getId())
                .userName(userRepository.findById(board.getUserId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER)).getUserName())
                .title(board.getTitle())
                .contents(board.getContents())
                .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .categoryName(category.getCategoryName())
                .commentList(commentList)
                .build();
    }
}
