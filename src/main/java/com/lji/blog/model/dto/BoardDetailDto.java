package com.lji.blog.model.dto;

import lombok.*;

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
}
