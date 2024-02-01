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
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            SiteUserForm userform = mapper.readValue(request.getInputStream(),SiteUserForm.class);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userform.getUserid(),userform.getPassword()
                    , List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
            return manager.authenticate(token);

        }catch (Exception e) {

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

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
