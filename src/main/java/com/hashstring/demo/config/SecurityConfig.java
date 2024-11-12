package com.hashstring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure() // Enforces HTTPS for all requests
                )
                .headers(headers -> headers
                        .xssProtection(xss -> xss.and())
                        .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'"))
                        .frameOptions(frame -> frame.deny())
                )
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}