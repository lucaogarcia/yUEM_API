package com.example.yUEM.service;

import com.example.yUEM.dto.UserPostDto;
import com.example.yUEM.model.UserPost;
import com.example.yUEM.model.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPostService {

    @Autowired
    private UserPostRepository userPostRepository;

    public List<UserPostDto> getAllUserPosts() {
        List<UserPost> userPosts = userPostRepository.findAll();
        return getUserPostDtos(userPosts);
    }

    public List<UserPostDto> getUserPosts(Long id) {
        List<UserPost> userPosts = userPostRepository.findByUserId(id);
        return getUserPostDtos(userPosts);
    }

    private List<UserPostDto> getUserPostDtos(List<UserPost> userPosts) {
        List<UserPostDto> userPostDTOs = new ArrayList<>();

        for (UserPost userPost : userPosts) {
            UserPostDto dto = new UserPostDto();
            dto.setId(userPost.getId());
            dto.setMessage(userPost.getMessage());
            dto.setLikePost(userPost.getLikePost());
            dto.setUserId(userPost.getUser().getId());

            userPostDTOs.add(dto);
        }

        return userPostDTOs;
    }

    public List<UserPostDto> getAllPostsByUserId(Long id) {
        return null;
    }

    public List<String> getRanking() {
        return null;
    }
}
