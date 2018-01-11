package com.martinodutto.tpt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final StatelessAuthenticationFilter statelessAuthenticationFilter;
    private final UserDetailsService userService;

    @Autowired
    public WebSecurityConfig(StatelessAuthenticationFilter statelessAuthenticationFilter, UserDetailsService userService) {
        this.statelessAuthenticationFilter = statelessAuthenticationFilter;
        this.userService = userService;
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
            .headers()
                .frameOptions().disable() // needed to make accessible the H2 web console
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("'Bearer token_type=\"JWT\"'"));

        // very important: the session creation is set to stateless because otherwise Spring Security shares the same instance of
        // SecurityContext between all the threads of the same session. This would be a problem, because we dynamically set
        // the authentication object in a per-request scope, and so we would have concurrency problems if the context was shared.
        // More details at https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#tech-intro-sec-context-persistence
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

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

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
