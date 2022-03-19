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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    private int postCount;
}
