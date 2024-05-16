package com.RestApiTemplate.RestApiTemplate.Services;

import com.RestApiTemplate.RestApiTemplate.commons.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts ;
    public PostService() {
        posts = new ArrayList<>() ;
        posts.add(new Post("123")) ;
        posts.add(new Post("123")) ;
        posts.add(new Post("123")) ;
    }
    public List<Post> getPosts() {
        return posts ;
    }
    public Post getPostById(long id) {
        return posts.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null) ;
    }
    public void addPost(Post post) {
        post.setId(posts.size()) ;
        posts.add(post) ;
    }
    public void deletePost(long id) {
        posts.removeIf(x -> x.getId() == id) ;
    }
}
