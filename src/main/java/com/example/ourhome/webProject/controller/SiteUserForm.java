package com.example.ourhome.webProject.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    @NotEmpty(message = "ID는 필수 입력 항목 입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수 입력 항목 입니다.")
    private String password;
    private String email;
}
