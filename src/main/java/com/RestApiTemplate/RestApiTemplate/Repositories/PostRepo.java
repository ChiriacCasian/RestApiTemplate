package com.RestApiTemplate.RestApiTemplate.Repositories;

import com.RestApiTemplate.RestApiTemplate.commons.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
