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

    //createUser
    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto requestDto) {
        CreateUserResponseDto userResponseDto = userService.createUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    //getUserById(id)
    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponseDto> getUserById(@PathVariable Long id) {
        CreateUserResponseDto userResponseDto = userService.getUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }

    //getAllUsers
    @GetMapping
    public ResponseEntity<List<CreateUserResponseDto>> getAllUsers() {
        List<CreateUserResponseDto> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    //softDelete(id)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> deleteUserSoftly(@PathVariable Long id) {
        userService.deleteUserSoftly(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //hardDelete(id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserHardly(@PathVariable Long id) {
        userService.deleteUserHardly(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //updateUserById(id,user)
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUserById(@PathVariable Long id, @RequestBody @Valid UpdateUserRequestDto requestDto) {
        UpdateUserResponseDto responseDto = userService.updateUserById(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }

    //getAllWithSoftDeleted --Only for companyUses
    @GetMapping("/get-all-with-soft-deleted-hr@team")
    public ResponseEntity<List<CreateUserResponseDto>> getAllWithSoftDeleted() {
        List<CreateUserResponseDto> users = userService.getAllWithSoftDeleted();

        return ResponseEntity.ok(users);
    }

    //getAllSoftDeleted
    @GetMapping("/get-all-soft-deleted")
    public ResponseEntity<List<SoftDeleteResponseDto>> getAllSoftDeleted() {
        List<SoftDeleteResponseDto> responseDtoList = userService.getAllSoftDeleted();

        return ResponseEntity.ok(responseDtoList);
    }


}
