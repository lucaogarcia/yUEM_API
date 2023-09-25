package com.example.yUEM.controller;
import com.example.yUEM.model.User;
import com.example.yUEM.model.UserPost;
import com.example.yUEM.model.UserPostRepository;
import com.example.yUEM.model.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")

public class UserController {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private UserPostRepository postrepository;

    @GetMapping
    public List<User> getAll(){
        return userrepository.findAll();
    }

    //Ver se o usuario existe para login
    @GetMapping("/Login/{email}/{password}")
    public boolean login(@PathVariable String email, @PathVariable String password){
        User user = userrepository.findByEmail(email);
        if(user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping("/CreateUser")
    public void createUser(@RequestBody User user){
        userrepository.save(user);
    }

    @PutMapping("/UpdateUser/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        User user1 = userrepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        userrepository.save(updateuser(user, user1));
    }

    private User updateuser(User user, User user1){
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setCourse(user.getCourse());
        user1.setAge(user.getAge());
        user1.setAnonymous(user.isAnonymous());
        user1.setGender(user.getGender());

        return user1;
    }

    @PostMapping("/{user_id}/CreatePost")
    public void createPost(@PathVariable Long user_id, @RequestBody UserPost userpost){
        User user = userrepository.findById(user_id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        userpost.setUsers(user);
        postrepository.save(userpost);
    }

    @GetMapping("/{user_id}/Posts")
    public List<UserPost> getPosts(@PathVariable Long user_id){
        User user = userrepository.findById(user_id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        List<UserPost> userPosts = postrepository.findByUser(user);
        return userPosts;
    }

    @PutMapping("{user_id}/UpdatePost/{post_id}")
    public void updatePost(@PathVariable Long post_id, @RequestBody UserPost userpost){
        UserPost post = postrepository.findById(post_id).orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));
        postrepository.save(updatepost(userpost, post));
    }
    private UserPost updatepost(UserPost userpost, UserPost post){
        post.setMessage(userpost.getMessage());
        post.setLikePost(userpost.getLikePost());
        return post;
    }

    @DeleteMapping("/{user_id}/DeletePost/{post_id}")
    public void deletePost(@PathVariable Long post_id){
        postrepository.deleteById(post_id);
    }

}
