package com.example.ourhome.webProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    //비어있는 폼 전달
    @GetMapping("/login")
    public String createForm(Model model, SiteUserForm form){
        model.addAttribute("siteUserForm", form);
        return "login";
    }
}
