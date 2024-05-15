package com.RestApiTemplate.RestApiTemplate.Controllers;

import com.RestApiTemplate.RestApiTemplate.ErrorHandling.UserNotFoundException;
import com.RestApiTemplate.RestApiTemplate.Services.UserService;
import com.RestApiTemplate.RestApiTemplate.commons.AppUser;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService ;
    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return userService.getUsers() ;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody AppUser appUser) {
        userService.addUser(appUser) ;
        return ResponseEntity.ok().build() ;
    }
    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable long id) {
        AppUser appUser = userService.getUserById(id) ;
        if(appUser == null) {
            throw new UserNotFoundException("User with id: " + id + " not found") ;
        }
        return appUser ;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        if(userService.getUserById(id) == null) {
            throw new UserNotFoundException("User with id: " + id + " does not exist") ;
        }
        userService.deleteUser(id) ;
        return ResponseEntity.ok().build() ;
    }
}
