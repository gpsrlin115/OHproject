package com.example.ourhome.webProject.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class SiteUserForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String username;

    @NotEmpty(message = "ID는 필수 입력 항목 입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수 입력 항목 입니다.")
    private String password;
    private String email;

}
