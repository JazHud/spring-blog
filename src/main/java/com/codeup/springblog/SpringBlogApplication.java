package com.codeup.springblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBlogApplication {
//run HelloController.java from here
    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }

}


//  put this in browser to see after running main localhost:8080/hello