package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.controller.SiteUserForm;
import com.example.ourhome.webProject.controller.UserDetail;
import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

   private final SiteUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



       Optional<SiteUser> user =  repository.findByUserid(username);

       if(user.isPresent()) {
           UserDetail detail = new UserDetail(user.get());

           return detail;
       }

        throw new UsernameNotFoundException("회원가입이 된 유저가 아님");
    }

}
