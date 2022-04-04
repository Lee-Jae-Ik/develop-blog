package com.lji.blog.model.schema;

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
    private LocalDateTime createdDate;

    @Column(name = "contents")
    private String contents;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime modifiedDate;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @OneToMany
    private List<Comment> commentList;

    @Builder
    public Board(Long id, Long userId, String title, LocalDateTime createdDate, String contents, LocalDateTime modifiedDate, Long categoryId, Category category, List<Comment> commentList) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.createdDate = createdDate;
        this.contents = contents;
        this.modifiedDate = modifiedDate;
        this.categoryId = categoryId;
        this.category = category;
        this.commentList = commentList;
    }
}
