package com.lji.blog.model.schema;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@AllArgsConstructor
@Builder
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

}
