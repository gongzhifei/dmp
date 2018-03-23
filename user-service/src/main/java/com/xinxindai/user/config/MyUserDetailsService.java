package com.xinxindai.user.config;

import com.xinxindai.user.entity.Menu;
import com.xinxindai.user.entity.Role;
import com.xinxindai.user.entity.User;
import com.xinxindai.user.entity.UserInfo;
import com.xinxindai.user.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongzhifei
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMenuReposirtory roleMenuReposirtory;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("平台不支持此用户，请联系管理员开通平台用户!");
        }
        List<Integer> roleIds = userRoleRepository.queryByRoleId(user.getId());
        List<Role> roles = roleRepository.findByIdIn(roleIds);
        List<Integer> menuIds = roleMenuReposirtory.queryByRoleId(roleIds);
        List<Menu> menus = menuRepository.findByIdIn(menuIds);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<Integer> typeIds = userTypeRepository.findByUserId(user.getId());
        for (Menu menu : menus) {
            if (StringUtils.isNotBlank(menu.getUrl())) {
                grantedAuthorities.add(new SimpleGrantedAuthority(menu.getUrl()));
            }
        }
        return new UserInfo(user.getId(),username, "e10adc3949ba59abbe56e057f20f883e", grantedAuthorities, menus, typeIds);
    }
}
