package com.lms.user_service.service;

import com.lms.user_service.dto.UserRequestDto;
import com.lms.user_service.dto.UserResponseDto;
import com.lms.user_service.model.User;
import com.lms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        userRepository.save(user);

        return mapToResponse(user);
    }

    public Optional<UserResponseDto> getUserById(Long userId) {
        return userRepository.findById(userId).map(this::mapToResponse);
    }

    public Optional<UserResponseDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::mapToResponse);
    }

    public boolean userExists(Long userId) {
        return userRepository.existsByUserId(userId);
    }

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(LocalDateTime.now().toString())
                .build();
    }
}

