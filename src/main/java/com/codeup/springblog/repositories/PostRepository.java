package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByBodyContaining(String wordSearch);
    Post findByTitle(String title);
    Post findById(long id);
//    Post deletePostById(long id);
}


