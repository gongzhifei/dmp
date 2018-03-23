package com.xinxindai.user.repository;

import com.xinxindai.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author gongzhifei
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {


    List<Role> findByIdIn(List<Integer> ids);

}
