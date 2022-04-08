package com.lji.blog.model.schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Auth
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Builder
    public Auth(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }
}
