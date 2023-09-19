package com.example.yUEM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "userpost")
@Entity(name = "UserPost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "likepost")
    private Integer likePost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User user;

    public void setUsers(User user) {
        this.user = user;
    }
}

