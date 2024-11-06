package com.robinland.pos.RobinlandPos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()

                // User Master
                .antMatchers(HttpMethod.GET, "/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/**").permitAll()

                // Role Master
                .antMatchers(HttpMethod.GET, "/role/**").permitAll()
                .antMatchers(HttpMethod.POST, "/role/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/role/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/role/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

//    @Bean
//    public UserDetailsService users(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
