package com.RestApiTemplate.RestApiTemplate.Controllers;

import com.RestApiTemplate.RestApiTemplate.ErrorHandling.PostNotFoundException;
import com.RestApiTemplate.RestApiTemplate.ErrorHandling.UserNotFoundException;
import com.RestApiTemplate.RestApiTemplate.Repositories.PostRepo;
import com.RestApiTemplate.RestApiTemplate.Repositories.UserRepo;
import com.RestApiTemplate.RestApiTemplate.Services.PostService;
import com.RestApiTemplate.RestApiTemplate.commons.AppUser;
import com.RestApiTemplate.RestApiTemplate.commons.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController { /// 123123123
    @Autowired
    PostService postService ;
    @Autowired
    PostRepo postRepo ;
    @Autowired
    UserRepo userRepo ;
    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable long id) {
        if(userRepo.findById(id).isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " does not exist") ;
        }
        return userRepo.findById(id).get().getPosts() ;
    }
    @PostMapping("/users/{id}/posts")
    public String createPost(@RequestBody Post post, @PathVariable long id) {
        AppUser appUser = userRepo.findById(id).get() ;
        post.setAppUser(appUser); ;
        if(userRepo.findById(post.getAppUser().getId()).isEmpty())
            throw new UserNotFoundException("User with id: " + post.getAppUser().getId() + " does not exist") ;
        postRepo.save(post) ;
        userRepo.findById(post.getAppUser().getId()).get().getPosts().add(post) ;
        return "200 : Post created" ;
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        if(postService.getPostById(id) == null) {
            throw new PostNotFoundException("Post with id: " + id + " does not exist") ;
        }
        postService.deletePost(id) ;
        return ResponseEntity.ok().build() ;
    }
}
