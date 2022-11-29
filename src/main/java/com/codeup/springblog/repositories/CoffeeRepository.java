package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

// the long below is the data type of the primary key
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    Coffee findById(long id);
}
