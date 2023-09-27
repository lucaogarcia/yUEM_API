package com.example.yUEM.service;

import com.example.yUEM.dto.UserPostDto;
import com.example.yUEM.model.User;
import com.example.yUEM.model.UserRepository;
import com.example.yUEM.dto.UserWithPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPostService userPostService;

    public List<UserWithPostDto> getAllUsersWithPosts() {
        List<User> users = userRepository.findAll();
        List<UserWithPostDto> usersWithPosts = new ArrayList<>();

        for (User user : users) {
            UserWithPostDto userWithPosts = new UserWithPostDto();
            userWithPosts.setId(user.getId());
            userWithPosts.setName(user.getName());
            userWithPosts.setEmail(user.getEmail());
            userWithPosts.setPassword(user.getPassword());
            userWithPosts.setCourse(user.getCourse());
            userWithPosts.setGender(user.getGender());
            userWithPosts.setAge(user.getAge());
            userWithPosts.setAnonymous(user.isAnonymous());

            // Recupere os posts do usuário e converta-os em UserPostDTO
            List<UserPostDto> userPostDTOs = userPostService.getUserPosts(user.getId());
            userWithPosts.setPosts(userPostDTOs);

            usersWithPosts.add(userWithPosts);
        }

        return usersWithPosts;
    }

}
