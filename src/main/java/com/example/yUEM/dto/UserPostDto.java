package com.example.yUEM.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
    private Long id;
    private String message;
    private int likePost;
    private Long userId;

    public UserPostDto() {
    }
}
