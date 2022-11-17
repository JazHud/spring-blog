package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;

@Controller
@RequestMapping("/posts") /// <-- will at to the @GetMapping's below
public class PostController {

    @GetMapping
    @ResponseBody
    public String allPosts() {
        return "Here are all the posts:...";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String onePost(@PathVariable long id) {
        return "Here is post number: " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createPost() {
        return "Here is the for to create a post!";
    }

}


