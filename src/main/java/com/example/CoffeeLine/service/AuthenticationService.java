package com.example.CoffeeLine.service;

import com.example.CoffeeLine.domain.User;
import com.example.CoffeeLine.dto.auth.SignInRequestDto;
import com.example.CoffeeLine.dto.auth.SignUpRequestDto;

public interface AuthenticationService {
    String signUp(SignUpRequestDto signUpRequestDto);
    String signIn(SignInRequestDto signInRequestDto);
}
