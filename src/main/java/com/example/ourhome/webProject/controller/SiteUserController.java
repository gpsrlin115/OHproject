package com.example.ourhome.webProject.controller;


import com.example.ourhome.webProject.model.dto.UserLoginRequest;
import com.example.ourhome.webProject.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class SiteUserController {

    private final SiteUserService siteUserService;


    //회원가입
    @PostMapping("/register")
    public ResponseEntity<String> join(@RequestBody SiteUserForm form) {

        siteUserService.join(form.getUserid(), form.getPassword(), form.getUsername(),
                form.getEmail());
        return ResponseEntity.ok().body("회원가입 성공");
    }


    //test
    @PostMapping("/hellotest")
    public String hello(){
        return "Hello World!";
    }

}
