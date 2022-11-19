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
        Post post1 = new Post("First", "This is my first post!", 1);
        Post post2 = new Post("Second", "Hey guys, I'm back!", 2);
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post1 = new Post("First", "This is my first post!", 1);
        Post post2 = new Post("Second", "Hey guys, I'm back!", 2);
        Post post3 = new Post("Third", "Woah! Check this out!", 3);
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2, post3));
        Post post = null;
        for (Post userPost : allPosts){
            if (userPost.getId() == id){
                post = userPost;
            }
        }
        model.addAttribute("post", post);
//        model.addAttribute("postId", id);
        return "/posts/show";
    }


    @GetMapping("/create")
    public String createPostForm() {
        return "post-form";
    }

    @PostMapping("/create")
    String addPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body){
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:posts/index";
    }

}


