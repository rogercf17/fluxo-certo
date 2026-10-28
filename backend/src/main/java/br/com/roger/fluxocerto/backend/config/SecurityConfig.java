package br.com.roger.fluxocerto.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers("/actuator/health/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated());
        return httpSecurity.build();
    }
}
