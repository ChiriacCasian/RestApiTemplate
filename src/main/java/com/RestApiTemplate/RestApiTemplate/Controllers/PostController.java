package com.RestApiTemplate.RestApiTemplate.Controllers;

import com.RestApiTemplate.RestApiTemplate.ErrorHandling.PostNotFoundException;
import com.RestApiTemplate.RestApiTemplate.Services.PostService;
import com.RestApiTemplate.RestApiTemplate.commons.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService ;
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts() ;
    }
    @PostMapping("/posts")
    public String createPost(@RequestBody Post post) {
        postService.addPost(post) ;
        return "200 : Post created" ;
    }
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id) ;
        if(post == null){
            throw new PostNotFoundException("Post not found") ;
        }
        return post ;
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
