package com.codehidder.user_demo.controller;

import com.codehidder.user_demo.dto.*;
import com.codehidder.user_demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody @Valid CreateUserRequestDto requestDto) {

        UserResponseDto responseDto = userService.createUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id) {

        UserResponseDto responseDto = userService.getUserById(id);

        return ResponseEntity.ok(responseDto);
    }

    // Get All Active Users
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        List<UserResponseDto> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // Soft Delete User
    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDeleteUser(
            @PathVariable Long id) {

        userService.softDeleteUser(id);

        return ResponseEntity.noContent().build();
    }

    // Hard Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeleteUser(
            @PathVariable Long id) {

        userService.hardDeleteUser(id);

        return ResponseEntity.noContent().build();
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUserById(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequestDto requestDto) {

        UpdateUserResponseDto responseDto =
                userService.updateUserById(id, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    // Get All Users Including Soft Deleted Users
    @GetMapping(params = "includeDeleted")
    public ResponseEntity<List<UserResponseDto>> getAllUsersIncludingDeleted(
            @RequestParam boolean includeDeleted) {

        List<UserResponseDto> users =
                userService.getAllUsersIncludingDeleted();

        return ResponseEntity.ok(users);
    }

    // Get Soft Deleted Users
    @GetMapping("/deleted")
    public ResponseEntity<List<SoftDeleteResponseDto>> getSoftDeletedUsers() {

        List<SoftDeleteResponseDto> responseDtoList =
                userService.getSoftDeletedUsers();

        return ResponseEntity.ok(responseDtoList);
    }
}