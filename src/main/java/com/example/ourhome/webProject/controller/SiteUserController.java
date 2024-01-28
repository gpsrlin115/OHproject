package com.example.ourhome.webProject.controller;


import com.example.ourhome.webProject.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class SiteUserController {

    private final SiteUserService siteUserService;

    //비어있는 폼 전달
    @GetMapping("/login")
    public String createForm(Model model, SiteUserForm form){
        model.addAttribute("siteUserForm", form);
        return "login";
    }

    //회원가입
    @PostMapping("/login")
    public ResponseEntity<String> join(@RequestBody SiteUserForm form) {
        siteUserService.join(form.getUserid(), form.getPassword(), form.getUsername(), form.getEmail());
        return ResponseEntity.ok().body("회원가입 성공");
    }

    //로그인
}
