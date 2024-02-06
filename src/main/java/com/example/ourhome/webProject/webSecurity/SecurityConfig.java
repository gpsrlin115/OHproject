package com.example.ourhome.webProject.webSecurity;

import com.example.ourhome.webProject.Filter.JwtCheckFilter;
import com.example.ourhome.webProject.Filter.LoginFilter;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // 웹 보안 활성화
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final SiteUserRepository repository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationConfiguration configuration) throws Exception {


        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.httpBasic(AbstractHttpConfigurer :: disable);

        httpSecurity.sessionManagement(seeion -> seeion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers("/api/users/**","api/v1/posts/**","/login").permitAll()

                        .anyRequest().authenticated());
        httpSecurity.addFilterBefore(new LoginFilter(configuration.getAuthenticationManager()),
                UsernamePasswordAuthenticationFilter.class);

        httpSecurity.addFilterBefore(new JwtCheckFilter(repository), UsernamePasswordAuthenticationFilter.class);


        httpSecurity.exceptionHandling(hanlder -> hanlder.accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                log.info("핸들러 테스트 ");
            }
        }));

        httpSecurity.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").usernameParameter("userid")
                .passwordParameter("password").failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        log.info("핸들러 ={}",exception.fillInStackTrace());
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    }
                }).defaultSuccessUrl("/"));

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer customizer(){
        return (web) ->web.ignoring().requestMatchers("/css/**","/image/**","templates/**");
    }
    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}