package com.example.ourhome.webProject.Filter;

import com.example.ourhome.webProject.Util.JwtUtils;
import com.example.ourhome.webProject.controller.SiteUserForm;
import com.example.ourhome.webProject.controller.UserDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
@Component
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager manager;

    public LoginFilter(AuthenticationManager manager){
        super(manager);
        this.manager = manager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) //제일먼저 들어옴 (로그인시도, 유저인증 x)
            throws AuthenticationException {

        try {
            ObjectMapper mapper = new ObjectMapper(); //form 데이터 -> json으로 변환
            SiteUserForm userform = mapper.readValue(request.getInputStream(),SiteUserForm.class);  //json -> 객체로 변환
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userform.getUserid(),userform.getPassword() //id,비번 "ROLE_ANONYMOUS" 권한
                    , List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));//인증이 안된 유저를 토큰으로 만들어서
            return manager.authenticate(token);//AuthenticationManager에게 전달

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetail detail = (UserDetail)authResult.getPrincipal();

        String jwtToken = JwtUtils.createJwtToken(detail);

        if(jwtToken != null){
            response.addHeader("Authorization","Bearer "+jwtToken);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }

        //-> 여기
//        super.successfulAuthentication(request, response, chain, authResult);
    }
}
