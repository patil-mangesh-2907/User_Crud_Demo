package com.codehidder.user_demo.dto;

import java.time.LocalDateTime;

public class SoftDeleteResponseDto {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String mobile;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime softDeletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getSoftDeletedAt() {
        return softDeletedAt;
    }

    public void setSoftDeletedAt(LocalDateTime softDeletedAt) {
        this.softDeletedAt = softDeletedAt;
    }
}
