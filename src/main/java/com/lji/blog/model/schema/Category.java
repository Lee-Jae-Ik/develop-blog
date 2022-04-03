package com.lji.blog.model.schema;

import lombok.*;

import javax.persistence.*;

/**
 * Category
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-03-19
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(name = "post_count")
    @Setter
    private int postCount;

    @Builder
    public Category(Long id, String categoryName, int postCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.postCount = postCount;
    }
}
