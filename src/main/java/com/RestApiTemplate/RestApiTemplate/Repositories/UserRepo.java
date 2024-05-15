package com.RestApiTemplate.RestApiTemplate.Repositories;

import com.RestApiTemplate.RestApiTemplate.commons.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
}
