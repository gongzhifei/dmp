package com.xinxindai.user.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author gongzhifei
 */
public class UserInfo extends User implements Serializable {

    public UserInfo(Integer id,String username, String password, Collection<? extends GrantedAuthority> authorities, List<Menu> menus, List<Integer> userTypes) {
        super(username, password, authorities);
        this.id = id;
        this.menus = menus;
        this.userTypes = userTypes;
    }

    private Integer id;

    private List<Menu> menus;

    private List<Integer> userTypes;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Integer> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<Integer> userTypes) {
        this.userTypes = userTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
