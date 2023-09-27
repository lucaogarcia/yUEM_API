package com.example.yUEM.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findByUser(User user);

    List<UserPost> findByUserId(Long id);
}
