package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 있는 필드만으로 생성자를 생성
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;

    //회원 가입
    @Transactional
    public Long join(SiteUser siteUser){
        validateDuplicateSiteUser(siteUser); // 중복 회원 검증
        siteUserRepository.save(siteUser);
        return siteUser.getId();
    }

    private void validateDuplicateSiteUser(SiteUser siteUser) {
        //EXCEPTION
        List<SiteUser> findUsers = siteUserRepository.findByName(siteUser.getUsername());
        if (!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재 하는 유저명 입니다.");
        }
    }

    //회원 전체 조회
    public List<SiteUser> findUsers(){
        return siteUserRepository.findAll();
    }

    public SiteUser findOne(Long userId){
        return siteUserRepository.findOne(userId);
    }

}
