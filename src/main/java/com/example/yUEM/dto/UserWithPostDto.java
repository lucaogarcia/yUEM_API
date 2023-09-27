package com.example.yUEM.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithPostDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String course;
    private String gender;
    private int age;
    private boolean anonymous;
    private List<UserPostDto> posts;


    public UserWithPostDto() {
    }

}
