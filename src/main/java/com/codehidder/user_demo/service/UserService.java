package com.codehidder.user_demo.service;

import com.codehidder.user_demo.dto.*;
import com.codehidder.user_demo.entity.User;
import com.codehidder.user_demo.exception.DuplicateResourceException;
import com.codehidder.user_demo.exception.ResourceNotFoundException;
import com.codehidder.user_demo.mapper.UserMapper;
import com.codehidder.user_demo.notification.NotificationService;
import com.codehidder.user_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final NotificationService notificationService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.notificationService = notificationService;
    }

    public UserResponseDto createUser(CreateUserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new DuplicateResourceException("User with email: " + userRequestDto.getEmail() + " already exists");
        }

        User user = userRepository.save(userMapper.mapCreateUserRequestDtoToEntity(userRequestDto));

        notificationService.sendNotification("User created successfully: " + user.getEmail());

        return userMapper.mapEntityToUserResponseDto(user);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        return userMapper.mapEntityToUserResponseDto(user);

    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAllByDeletedIsFalse();

        return users.stream()
                .map(userMapper::mapEntityToUserResponseDto)
                .toList();
    }

    public void softDeleteUser(Long id) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        user.setDeleted(true);

        user.setSoftDeletedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public void hardDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        userRepository.deleteById(id);
    }

    public UpdateUserResponseDto updateUserById(Long id, UpdateUserRequestDto userRequestDto) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        user.setName(userRequestDto.getName());
        user.setAddress(userRequestDto.getAddress());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return userMapper.mapEntityToUpdateUserResponseDto(savedUser);
    }

    public List<UserResponseDto> getAllUsersIncludingDeleted() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::mapEntityToUserResponseDto)
                .toList();
    }

    public List<SoftDeleteResponseDto> getSoftDeletedUsers() {
        List<User> users = userRepository.findAllByDeletedIsTrue();

        return users.stream()
                .map(userMapper::mapEntityToSoftDeleteResponse)
                .toList();
    }
}
