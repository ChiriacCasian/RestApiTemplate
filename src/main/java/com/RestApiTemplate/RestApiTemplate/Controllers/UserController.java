package com.RestApiTemplate.RestApiTemplate.Controllers;

import com.RestApiTemplate.RestApiTemplate.ErrorHandling.UserNotFoundException;
import com.RestApiTemplate.RestApiTemplate.Repositories.UserRepo;
import com.RestApiTemplate.RestApiTemplate.Services.UserService;
import com.RestApiTemplate.RestApiTemplate.commons.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

@RestController
public class UserController {
    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepo userRepo ;
    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return userRepo.findAll() ;
    }
    @PostMapping("/users")
    @JsonIgnore()
    public ResponseEntity<User> createUser(@Valid @RequestBody AppUser appUser) {
        userRepo.save(appUser) ;
        return ResponseEntity.ok().build() ;
    }
    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable long id) {
        AppUser appUser = userRepo.findById(id).orElse(null);
        if(appUser == null) {
            throw new UserNotFoundException("User with id: " + id + " not found") ;
        }
        return appUser ;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        if(userRepo.findById(id).orElse(null) == null) {
            throw new UserNotFoundException("User with id: " + id + " does not exist") ;
        }
        userRepo.deleteById(id); ;
        return ResponseEntity.ok().build() ;
    }

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/good-morning")///internationalization i18n
    public String getGoodMorningMessage() {
        return messageSource.getMessage("good.morning.message",
                null,
                LocaleContextHolder.getLocale());
    }
}
