package com.example.CoffeeLine.web;

import com.example.CoffeeLine.dto.user.ChangeUserRoleRequestDto;
import com.example.CoffeeLine.dto.user.UserListResponseDto;
import com.example.CoffeeLine.dto.user.UserUpdateRequestDto;
import com.example.CoffeeLine.service.UserService;
import com.example.CoffeeLine.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok()
                .body(new UserListResponseDto(
                        userService.getAllUsers().stream()
                                .map(userMapper::toUserResponseDto)
                                .toList()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok()
                .body(userMapper.toUserResponseDto(
                        userService.getUserById(userId)));
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok()
                .body(userMapper.toUserResponseDto(
                        userService.updateUser(userUpdateRequestDto)));
    }

    @PutMapping("/role/add")
    public ResponseEntity<Object> addRoleToUser(@Valid @RequestBody ChangeUserRoleRequestDto changeUserRoleRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMapper.toUserResponseDto(
                        userService.addRoleToUser(changeUserRoleRequestDto)));
    }

    @PutMapping("/role/remove")
    public ResponseEntity<Object> removeRoleFromUser(@Valid @RequestBody ChangeUserRoleRequestDto changeUserRoleRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMapper.toUserResponseDto(
                        userService.removeRoleFromUser(changeUserRoleRequestDto)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
