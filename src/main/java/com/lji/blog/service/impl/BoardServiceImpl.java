package com.lji.blog.service.impl;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.model.dto.*;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.schema.*;
import com.lji.blog.repository.*;
import com.lji.blog.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final BoardTagRepository boardTagRepository;

    public BoardServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository, CommentRepository commentRepository, BoardRepository boardRepository, BoardTagRepository boardTagRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.boardTagRepository = boardTagRepository;
    }

    @Override
    public Board saveBoard(BoardSaveDto boardSaveDto) {

        if (boardSaveDto.getBoradTagNameList().size() > 10) {
            throw new BlogApiRuntimeException(BlogApiResult.OVER_BOARD_TAG_COUNT);
        }

        Category findCategory = categoryRepository.findById(boardSaveDto.getCategoryId())
                .orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_CATEGORY));

        User findUser = userRepository.findById(boardSaveDto.getUserId())
                .orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER));

        List<BoardTag> boardTagList = new ArrayList<>();
        for (String boardTagName : boardSaveDto.getBoradTagNameList()) {
            BoardTag findBoardTag = Optional.ofNullable(boardTagRepository.findBoardTagByTagName(boardTagName))
                    .orElseGet(BoardTag::new);
            boardTagList.add(BoardTag.builder()
                            .id(findBoardTag.getId())
                            .tagName((findBoardTag.getTagName() != null) ? findBoardTag.getTagName() : boardTagName)
                            .tagCount((findBoardTag.getTagCount() != 0) ? findBoardTag.getTagCount() + 1 : 1)
                            .build());
        }
        boardTagRepository.saveAll(boardTagList);

        Board insertBoard = Board.builder()
                .userId(boardSaveDto.getUserId())
                .categoryId(boardSaveDto.getCategoryId())
                .title(boardSaveDto.getTitle())
                .contents(boardSaveDto.getContents())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .category(findCategory)
                .commentList(null)
                .boardTagList(boardTagList)
                .delYn(false)
                .build();

        findCategory.setPostCount(findCategory.getPostCount() + 1);
        boardRepository.save(insertBoard);

        return insertBoard;
    }

    @Override
    @Transactional
    public BoardListDto showBoardList(Pageable pageable, Long categoryId) {
        List<Board> boardList = boardRepository.findBoardByCategoryId(pageable,categoryId);

        List<BoardShowDto> boardShowDtoList = boardList.stream().map(board ->
                BoardShowDto.builder()
                        .id(board.getId())
                        .userName(userRepository.findById(board.getUserId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER)).getUserName())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .category(categoryRepository.findById(board.getCategoryId()).orElse(null))
                        .commentCount(commentRepository.countCommentByBoardId(board.getId()))
                        .build()).collect(Collectors.toList());

        return BoardListDto.builder()
                .boardList(boardShowDtoList)
                .totalBoardCount(boardList.size())
                .build();
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
                .boardTagList(board.getBoardTagList())
                .build();
    }

    @Override
    public BoardListDto searchBoardList(Pageable pageable, String title, String userName) {
        List<Board> boardList = boardRepository.searchBoardByTitleOrUserName(title,userName,pageable);

        List<BoardShowDto> boardShowDtoList = boardList.stream().map(board ->
                BoardShowDto.builder()
                        .id(board.getId())
                        .userName(userRepository.findById(board.getUserId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_USER)).getUserName())
                        .title(board.getTitle())
                        .createdDate(board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .category(categoryRepository.findById(board.getCategoryId()).orElse(null))
                        .commentCount(commentRepository.countCommentByBoardId(board.getId()))
                        .build()).collect(Collectors.toList());

        return BoardListDto.builder().boardList(boardShowDtoList).totalBoardCount(boardShowDtoList.size()).build();
    }

    @Override
    public BoardIdDto deleteBoardById(Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_BOARD));
        Category findCategory = categoryRepository.findById(findBoard.getCategoryId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.NOT_HAVE_CATEGORY));
        findCategory.setPostCount(findCategory.getPostCount() - 1);

        findBoard.getBoardTagList().forEach(boardTag -> {
            BoardTag findBoardTag = boardTagRepository.findById(boardTag.getId()).orElseThrow(() -> new BlogApiRuntimeException(BlogApiResult.SERVER_ERROR));
            findBoardTag.setTagCount(findBoardTag.getTagCount() - 1);
        });

        Board deleteBoard = Board.builder()
                .id(findBoard.getId())
                .categoryId(findBoard.getCategoryId())
                .category(findBoard.getCategory())
                .user(findBoard.getUser())
                .userId(findBoard.getUserId())
                .boardTagList(findBoard.getBoardTagList())
                .title(findBoard.getTitle())
                .contents(findBoard.getContents())
                .commentList(findBoard.getCommentList())
                .delYn(true)
                .build();
        boardRepository.save(deleteBoard);

        return BoardIdDto.builder().id(deleteBoard.getId()).build();
    }
}
