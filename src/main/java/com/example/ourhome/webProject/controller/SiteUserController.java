package com.example.ourhome.webProject.controller;

import com.example.ourhome.webProject.model.SiteUser;
import com.example.ourhome.webProject.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SiteUserController {

    private final SiteUserService siteUserService;

    @GetMapping("/register")
    public String createForm(Model model) {
        model.addAttribute("siteUserForm", new SiteUserForm());
        return "login";
    }

    @PostMapping("/register")
    public String createUser(@Valid SiteUserForm form, BindingResult result){
        if (result.hasErrors()){
            return "/register";
        }
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(form.getName());
        siteUser.setUserid(form.getUserid());
        siteUser.setEmail(form.getEmail());
        siteUser.setPassword(form.getPassword());
        siteUserService.join(siteUser); //저장

        return "redirect:/";
    }

}
