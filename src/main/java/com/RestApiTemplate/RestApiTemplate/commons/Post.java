package com.RestApiTemplate.RestApiTemplate.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    // because we don't want to load the user when we load the post
    // because there is no point since we ignore it with @JsonIgnore
    @JsonIgnore
    private AppUser appUser ;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Post() {
    }
    public Post(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
