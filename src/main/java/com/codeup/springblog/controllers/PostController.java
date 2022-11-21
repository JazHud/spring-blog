package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts") /// <-- will at to the @GetMapping's below
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }


    @GetMapping
    public String allPosts(Model model) {
        List <Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "Posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        if(postDao.getPostById(id) == null){
            return "redirect:/posts";
        }
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
//        model.addAttribute("postId", id);
        return "Posts/show";
    }

    @GetMapping("/create")
    public String createPostForm() {
        return "Posts/post-form";
    }

    @PostMapping("/create")
    public String addPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body){
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }

}


