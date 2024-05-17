package com.RestApiTemplate.RestApiTemplate.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /// make sure every request is authenticated
        http.authorizeRequests(x -> x.anyRequest().authenticated()) ;
        /// have a basic http authentication popup in the browser
        http.httpBasic(withDefaults()) ;
        /// disable CSRF
        http.csrf(CsrfConfigurer::disable);
        return http.build();
        /// this is just so i can commit
    }
}
