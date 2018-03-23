package com.xinxindai.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gongzhifei
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccess authenticationSuccess;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private MyAccessDenied accessDenied;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")
                .and()
                .addFilter(jwtLoginFilter())
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/authentication/require").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDenied)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public JWTLoginFilter jwtLoginFilter() throws Exception {
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter();
        jwtLoginFilter.setAuthenticationManager(authenticationManager());
        jwtLoginFilter.setAuthenticationSuccessHandler(authenticationSuccess);
        return jwtLoginFilter;
    }

}