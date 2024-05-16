package com.RestApiTemplate.RestApiTemplate.Services;

import com.RestApiTemplate.RestApiTemplate.commons.AppUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<AppUser> appUsers;
    public UserService() {
        appUsers = new ArrayList<>() ;
        appUsers.add(new AppUser("John", "123", "111")) ;
        appUsers.add(new AppUser("Jane", "123", "111")) ;
        appUsers.add(new AppUser("Doe", "123", "111")) ;
    }
    public List<AppUser> getUsers() {
        return appUsers;
    }
    public AppUser getUserById(long id) {
        return appUsers.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null) ;
    }
    public void addUser(AppUser appUser) {
        appUsers.add(appUser) ;
    }
    public void deleteUser(long id) {
        appUsers.removeIf(x -> x.getId() == id) ;
    }
}
