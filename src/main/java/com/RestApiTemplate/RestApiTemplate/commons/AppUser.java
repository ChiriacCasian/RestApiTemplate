package com.RestApiTemplate.RestApiTemplate.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private long id ;
    @Size(min = 2, message = "MIN 2 CHARS")
    private String name;
    @Size(min = 2, message = "MIN 2 CHARS")
    private String email;
    @NotNull(message = "MUST INSERT A PASSWORD")
    //@JsonIgnore
    private String password;

    public List<Post> getPosts() {
        return posts;
    }

    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<Post> posts ;

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public AppUser() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
