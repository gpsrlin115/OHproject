package com.example.ourhome.webProject.webSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 웹 보안 활성화
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                // 토큰을 사용하기 때문에 csrf 설정 disable
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/posts/*").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/hello/api-auth-test").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .getOrBuild();
    }
}
