package com.lji.blog.model.schema;

import lombok.*;

import javax.persistence.*;

/**
 * BoardTag
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/04/23
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "board_tag")
public class BoardTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "tag_count")
    @Setter
    private int tagCount;

    @Builder
    public BoardTag(Long id, String tagName, int tagCount) {
        this.id = id;
        this.tagName = tagName;
        this.tagCount = tagCount;
    }
}
