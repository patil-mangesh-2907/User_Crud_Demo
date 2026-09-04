package com.codehidder.user_demo.service;

import com.codehidder.user_demo.dto.*;
import com.codehidder.user_demo.entity.User;
import com.codehidder.user_demo.mapper.Mapper;
import com.codehidder.user_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    //createUser
    public CreateUserResponseDto createUser(CreateUserRequestDto userRequestDto) {
        User user = userRepository.save(mapper.mapCreateUserRequestDtoToEntity(userRequestDto));

        return mapper.mapEntityToCreateUserResponseDto(user);
    }

    //getUserById(id)
    public CreateUserResponseDto getUserById(Long id) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));

        return mapper.mapEntityToCreateUserResponseDto(user);

    }

    //getAllUsers
    public List<CreateUserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAllByDeletedIsFalse();

        return users.stream()
                .map(mapper::mapEntityToCreateUserResponseDto)
                .toList();
    }

    //softDelete(id)
    public void deleteUserSoftly(Long id) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));

        user.setDeleted(true);

        user.setSoftDeletedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    //hardDelete(id)
    public void deleteUserHardly(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));

        userRepository.deleteById(id);
    }

    //updateUserById(id,user)
    public UpdateUserResponseDto updateUserById(Long id, UpdateUserRequestDto userRequestDto) {
        User user = userRepository.findUserByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));

        user.setName(userRequestDto.getName());
        user.setAddress(userRequestDto.getAddress());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return mapper.mapEntityToUpdateUserResponseDto(savedUser);
    }

    //getAllWithSoftDeleted --Only for companyUses
    public List<CreateUserResponseDto> getAllWithSoftDeleted() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(mapper::mapEntityToCreateUserResponseDto)
                .toList();
    }

    //getAllSoftDeleted
    public List<SoftDeleteResponseDto> getAllSoftDeleted() {
        List<User> users = userRepository.findAllByDeletedIsTrue();

        return users.stream()
                .map(mapper::mapEntityToSoftDeleteResponse)
                .toList();
    }
}
