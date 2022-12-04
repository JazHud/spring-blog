package com.codeup.springblog.repositories;


import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
   User findById(long id);

   List<User> findByUsername(String username);

}
