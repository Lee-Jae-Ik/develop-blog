package com.lji.blog.model.dto;

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
@AllArgsConstructor
@Builder
public class BoardShowDto {

    private Long id;
    private Long userId;
    private String title;
    private String createdDate;
    private String modifiedDate;
}
