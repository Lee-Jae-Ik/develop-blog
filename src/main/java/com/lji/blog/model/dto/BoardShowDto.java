package com.lji.blog.model.dto;

import com.lji.blog.model.schema.Category;
import lombok.*;

/**
 * BoardVo
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardShowDto {

    private Long id;
    private Long userId;
    private String title;
    private String createdDate;
    private String modifiedDate;
    private Category category;

    @Builder
    public BoardShowDto(Long id, Long userId, String title, String createdDate, String modifiedDate, Category category) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.category = category;
    }
}
