package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts") /// <-- will at to the @GetMapping's below
public class PostController {

    @GetMapping
    public String allPosts(Model model) {
        Post post1 = new Post("First", "This is my first post!", 1);
        Post post2 = new Post("Second", "Hey guys, I'm back!", 2);
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("allPosts", allPosts);
        return "/posts/index";
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
    @ResponseBody
    public String createPost() {
        return "Here is the for to create a post!";
    }

}


