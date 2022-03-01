package com.lji.blog.model.vo;

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
public class BoardDetailVo {

    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private String modifiedDate;
}
