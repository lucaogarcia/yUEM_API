package com.example.yUEM.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "user")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "course")
    private String course;
    @Column(name = "age")
    private int age;
    @Column(name = "anonymous")
    private boolean anonymous;
    @Column(name = "gender")
    private String gender;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserPost> userPosts;
}
