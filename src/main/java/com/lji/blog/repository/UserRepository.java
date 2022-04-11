package com.lji.blog.repository;

import com.lji.blog.model.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/02/17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserIdAndUserPassword(String userId,String userPassword);
}
