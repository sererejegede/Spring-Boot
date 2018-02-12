package com.serere.user.secconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/")
                .hasAnyRole("admin", "user")
                .and()
                .formLogin();
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/view/**")
                .hasAnyRole("admin")
                .and()
                .formLogin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder
                .inMemoryAuthentication().withUser("user")
                .password("user")
                .roles("user");
        managerBuilder
                .inMemoryAuthentication().withUser("admin")
                .password("admin")
                .roles("user", "admin");
    }
}
