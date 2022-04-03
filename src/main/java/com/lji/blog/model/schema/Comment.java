package com.lji.blog.model.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Comment
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-03
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "comment_contents")
    private String commentContents;

    @Column(name = "created_date")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "board_id")
    private Long boardId;

    @Builder
    public Comment(String userName, String commentContents, LocalDateTime createdDate, Long boardId) {
        this.userName = userName;
        this.commentContents = commentContents;
        this.createdDate = createdDate;
        this.boardId = boardId;
    }
}
