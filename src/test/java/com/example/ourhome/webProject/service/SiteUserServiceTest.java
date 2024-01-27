package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.repository.SiteUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class SiteUserServiceTest {

    @Autowired SiteUserService siteUserService;
    @Autowired
    SiteUserRepository siteUserRepository;

    @Test
    void 회원가입() throws Exception {
        //given
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername("kim");
        
        //when
        Long savedId = siteUserService.join(siteUser);

        //then
        assertEquals(siteUser, siteUserRepository.findOne(savedId));
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        SiteUser user1 = new SiteUser();
        user1.setUsername("kim");
        SiteUser user2 = new SiteUser();
        user2.setUsername("kim");

        //when
        siteUserService.join(user1);
        try{
            siteUserService.join(user2);//예외 발생 해야 함
        } catch (IllegalStateException e){
            return;
        }

        //then
        fail("예외가 발생해야 한다.");

    }
}