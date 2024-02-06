package com.example.ourhome.webProject.controller;

import com.example.ourhome.webProject.model.SiteUser;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDetail implements UserDetails {

    SiteUser user;

    public UserDetail(SiteUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 로그인 유저정보 저장 (권한)
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> {
            return user.getRole(); // 인가를 이거로 확인
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
