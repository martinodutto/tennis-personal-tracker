package com.martinodutto.tpt.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // using JWT, we can disable CSRF and CORS
        http
                .csrf().disable()
                .cors().disable();

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
//                    .anyRequest().authenticated()
                    .and()
//                .formLogin()
//                    .loginPage("../static/login")
//                    .permitAll()
//                    .and()
                .logout()
                    .permitAll();
    }
}
