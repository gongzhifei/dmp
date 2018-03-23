package com.xinxindai.user.repository;

import com.xinxindai.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gongzhifei
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

}
