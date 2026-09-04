package com.codehidder.user_demo.mapper;

import com.codehidder.user_demo.dto.CreateUserRequestDto;
import com.codehidder.user_demo.dto.UserResponseDto;
import com.codehidder.user_demo.dto.SoftDeleteResponseDto;
import com.codehidder.user_demo.dto.UpdateUserResponseDto;
import com.codehidder.user_demo.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User mapCreateUserRequestDtoToEntity(CreateUserRequestDto requestDto) {
        User user = new User();

        user.setName(requestDto.getName());
        user.setAddress(requestDto.getAddress());
        user.setEmail(requestDto.getEmail());
        user.setMobile(requestDto.getMobile());
        user.setDeleted(false);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setSoftDeletedAt(null);

        return user;
    }

    public UserResponseDto mapEntityToUserResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setAddress(user.getAddress());
        responseDto.setEmail(user.getEmail());
        responseDto.setMobile(user.getMobile());
        responseDto.setCreatedAt(user.getCreatedAt());
        responseDto.setUpdatedAt(user.getUpdatedAt());

        return responseDto;
    }

    public UpdateUserResponseDto mapEntityToUpdateUserResponseDto(User user) {
        UpdateUserResponseDto responseDto = new UpdateUserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setAddress(user.getAddress());
        responseDto.setEmail(user.getEmail());
        responseDto.setMobile(user.getMobile());
        responseDto.setCreatedAt(user.getCreatedAt());
        responseDto.setUpdatedAt(user.getUpdatedAt());

        return responseDto;
    }

    public SoftDeleteResponseDto mapEntityToSoftDeleteResponse(User user) {
        SoftDeleteResponseDto responseDto = new SoftDeleteResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setAddress(user.getAddress());
        responseDto.setEmail(user.getEmail());
        responseDto.setMobile(user.getMobile());
        responseDto.setCreatedAt(user.getCreatedAt());
        responseDto.setUpdatedAt(user.getUpdatedAt());
        responseDto.setSoftDeletedAt(user.getSoftDeletedAt());
        return responseDto;
    }
}
