package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.exception.AppException;
import com.example.ourhome.webProject.exception.ErrorCode;
import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final이 있는 필드만으로 생성자를 생성
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;
    private final BCryptPasswordEncoder encoder;

    //회원 가입
    public String join(String userId, String password, String username, String email){
        validateDuplicateSiteUser(userId); // 중복 회원 검증
        SiteUser siteUser = SiteUser.builder()
                .userid(userId)
                .password(encoder.encode(password))
                .username(username)
                .email(email)
                .build();
        siteUserRepository.save(siteUser);
        return "SUCCESS";
    }

    private void validateDuplicateSiteUser(String userId) {
        //EXCEPTION
        siteUserRepository.findByUsername(userId)
                .ifPresent(SiteUser -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userId + "는 이미 존재하는 회원입니다.");
                });
    }

    //회원 전체 조회
    public List<SiteUser> findUsers(){
        return siteUserRepository.findAll();
    }

}
