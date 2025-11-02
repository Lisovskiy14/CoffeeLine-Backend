package com.example.CoffeeLine.service.impl;

import com.example.CoffeeLine.common.Role;
import com.example.CoffeeLine.domain.User;
import com.example.CoffeeLine.dto.user.ChangeUserRoleRequestDto;
import com.example.CoffeeLine.dto.user.UserUpdateRequestDto;
import com.example.CoffeeLine.service.UserService;
import com.example.CoffeeLine.service.exception.EmailAlreadyExistsException;
import com.example.CoffeeLine.service.exception.UserNotFoundException;
import com.example.CoffeeLine.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id.toString());
        }
        return user.get();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        User user = getUserById(UUID.fromString(userUpdateRequestDto.getId()));
        if (userUpdateRequestDto.getName() != null) {
            user.setName(userUpdateRequestDto.getName());
        }
        if (userUpdateRequestDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateRequestDto.getPhoneNumber());
        }
        if (userUpdateRequestDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(
                    userUpdateRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public User addRoleToUser(ChangeUserRoleRequestDto changeUserRoleRequestDto) {
        User user = getUserById(UUID.fromString(
                changeUserRoleRequestDto.getId()));
        user.getRoles().add(Role.valueOf(
                changeUserRoleRequestDto.getRole()));
        return userRepository.save(user);
    }

    @Override
    public User removeRoleFromUser(ChangeUserRoleRequestDto changeUserRoleRequestDto) {
        User user = getUserById(UUID.fromString(
                changeUserRoleRequestDto.getId()));
        user.getRoles().remove(Role.valueOf(
                changeUserRoleRequestDto.getRole()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
