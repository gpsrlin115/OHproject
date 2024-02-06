package com.example.ourhome.webProject.Filter;

import com.example.ourhome.webProject.Util.JwtUtils;
import com.example.ourhome.webProject.controller.UserDetail;
import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
@RequiredArgsConstructor
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

    private final SiteUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

        String authorizationToken = null;

       authorizationToken = request.getHeader("Authorization"); //이게 헤더에있는 jwt토큰꺼내는 거임

        if(request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/api/users/")
        || request.getRequestURI().startsWith("/api/admin/")
        || request.getRequestURI().equals("/")){
            //결국 200ok하겠다는 뜻
            filterChain.doFilter(request,response);
            return;
        }

        log.info("리퀘스트 경로 = {}",request.getRequestURI());


        //토큰 없거나 Bearer로 시작안하면 401에러하고 끝낼꺼임
        if(authorizationToken == null || !authorizationToken.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        try{
            String token = authorizationToken.split(" ")[1];
            Claims tokenUser = JwtUtils.getUsername(token);
            String findUser =  tokenUser.get("userid",String.class);

          Optional<SiteUser> user = repository.findByUserid(findUser);

          if(user.isEmpty()){ // 토큰에 유저가 비어있으면 401에러
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              return;
          }
            UserDetail detail = new UserDetail(user.get());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(detail,detail.getPassword(),detail.getAuthorities()); // 토큰에서 정보 추출

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request,response);

        }catch (Exception e){

            if(e instanceof io.jsonwebtoken.security.SignatureException) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

    }

}
