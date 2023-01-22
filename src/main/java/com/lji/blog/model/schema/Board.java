package com.lji.blog.model.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lji.blog.model.dto.BoardSaveDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Board
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "contents")
    private String contents;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToMany
    private List<Comment> commentList;

    @ManyToMany
    private List<BoardTag> boardTagList;

    @Column(name = "del_yn")
    private boolean delYn;

    @Builder
    public Board(Long id, Long userId, String title, LocalDateTime createdDate, String contents, LocalDateTime modifiedDate, Long categoryId, Category category, User user, List<Comment> commentList, List<BoardTag> boardTagList, boolean delYn) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.createdDate = createdDate;
        this.contents = contents;
        this.modifiedDate = modifiedDate;
        this.categoryId = categoryId;
        this.category = category;
        this.user = user;
        this.commentList = commentList;
        this.boardTagList = boardTagList;
        this.delYn = delYn;
    }

    public static Board createBoard(BoardSaveDto boardSaveDto, Category category, List<BoardTag> boardTagList) {
        return Board.builder()
                .userId(boardSaveDto.getUserId())
                .categoryId(boardSaveDto.getCategoryId())
                .title(boardSaveDto.getTitle())
                .contents(boardSaveDto.getContents())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .category(category)
                .commentList(null)
                .boardTagList(boardTagList)
                .delYn(false)
                .build();
    }
}
