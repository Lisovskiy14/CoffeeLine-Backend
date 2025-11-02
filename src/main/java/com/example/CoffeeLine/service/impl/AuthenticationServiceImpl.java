package com.example.CoffeeLine.service.impl;

import com.example.CoffeeLine.common.Role;
import com.example.CoffeeLine.domain.User;
import com.example.CoffeeLine.dto.auth.SignInRequestDto;
import com.example.CoffeeLine.dto.auth.SignUpRequestDto;
import com.example.CoffeeLine.security.CustomUserDetailsService;
import com.example.CoffeeLine.service.AuthenticationService;
import com.example.CoffeeLine.service.JwtService;
import com.example.CoffeeLine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @Override
    public String signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .build();

        userService.createUser(user);

        return jwtService.generateToken(user);
    }

    @Override
    public String signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDto.getEmail(),
                signInRequestDto.getPassword()
        ));

        UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequestDto.getEmail());

        return jwtService.generateToken(userDetails);
    }
}
