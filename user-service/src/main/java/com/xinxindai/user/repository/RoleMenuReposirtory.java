package com.xinxindai.user.repository;

import com.xinxindai.user.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gongzhifei
 */
public interface RoleMenuReposirtory extends JpaRepository<RoleMenu,Integer> {

    @Query(nativeQuery = true,value = "select menuid from dmp_role_menu where roleid in ?1")
    List<Integer> queryByRoleId(List<Integer> roleIds);

}
