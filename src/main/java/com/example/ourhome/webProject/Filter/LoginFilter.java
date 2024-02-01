package com.example.ourhome.webProject.Filter;

import com.example.ourhome.webProject.controller.SiteUserForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager manager;

    public LoginFilter(AuthenticationManager manager){
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

}
