package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Customer findById(long id);

}
