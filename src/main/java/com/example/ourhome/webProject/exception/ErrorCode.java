package com.example.ourhome.webProject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "USERNAME_DUPLICATED");

    private HttpStatus httpStatus;
    private String message;
}
