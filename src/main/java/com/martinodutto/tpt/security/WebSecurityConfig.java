package com.martinodutto.tpt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final StatelessAuthenticationFilter statelessAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(StatelessAuthenticationFilter statelessAuthenticationFilter) {
        this.statelessAuthenticationFilter = statelessAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // using JWT, we can disable CSRF and CORS
        http
                .csrf().disable()
                .cors().disable();

        http
                .authorizeRequests()
                    .antMatchers("/authentication/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
//                .logout()
//                    .permitAll()
//                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new Http401AuthenticationEntryPoint("'Bearer token_type=\"JWT\"'"));

        // we add the StatelessAuthenticationFilter to the filter chain
        http.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Disables FilterRegistration, so our StatelessAuthenticationFilter is included only in SpringSecurity usage and not in all filter chains.
     *
     * @param filter TPT stateless authentication filter.
     * @return FilterRegistrationBean.
     */
    @Bean
    public FilterRegistrationBean registration(StatelessAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
