package com.lji.blog.model.dto;

import com.lji.blog.exception.BlogApiRuntimeException;
import com.lji.blog.model.response.BlogApiResult;
import com.lji.blog.model.schema.Board;
import com.lji.blog.model.schema.BoardTag;
import com.lji.blog.model.schema.Category;
import com.lji.blog.model.schema.Comment;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * BoardDetailVo
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardDetailDto {

    private Long id;
    private String userName;
    private String title;
    private String contents;
    private String modifiedDate;
    private String categoryName;
    private List<Comment> commentList;
    private List<BoardTag> boardTagList;


    /* BoardDetailDto 생성 */
    public static BoardDetailDto createBoardDetailDtoUseBoardEntity(Board board, Category category, List<Comment> commentList) {

        return BoardDetailDto.builder()
                .id(board.getId())
                .userName(board.getUser().getUserName())
                .title(board.getTitle())
                .contents(board.getContents())
                .modifiedDate(board.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .categoryName(category.getCategoryName())
                .commentList(commentList)
                .boardTagList(board.getBoardTagList())
                .build();
    }
}
