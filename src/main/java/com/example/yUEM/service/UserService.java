package com.example.yUEM.service;

import com.example.yUEM.dto.UserPostDto;
import com.example.yUEM.model.User;
import com.example.yUEM.model.UserRepository;
import com.example.yUEM.dto.UserWithPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<String> printCoursesWithMostPosts() {
        // Recupere todos os usuários
        List<User> allUsers = userRepository.findAll();

        // Crie um mapa para armazenar o número de posts por curso
        Map<String, Long> coursePostCounts = allUsers.stream()
                .collect(Collectors.groupingBy(
                        User::getCourse,  // Obtém o atributo 'course' de cada usuário
                        Collectors.summingLong(user -> user.getUserPosts().size()) // Conta o número de posts para cada curso
                ));

        // Ordene os cursos com base no número de posts em ordem decrescente

        // Imprima os nomes dos cursos na ordem desejada

        return coursePostCounts.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

}
