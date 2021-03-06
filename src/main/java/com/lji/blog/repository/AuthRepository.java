package com.lji.blog.repository;

import com.lji.blog.model.schema.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * AuthRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@Repository
@Transactional
public interface AuthRepository extends JpaRepository<Auth, Long> {

    @Query("select a from Auth a join User u on a.user.id = u.id")
    Auth findAuthByUser(Long userId);
}
