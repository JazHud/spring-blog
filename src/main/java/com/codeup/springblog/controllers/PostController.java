package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts") /// <-- will at to the @GetMapping's below
public class PostController{

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/user-login")
    public String createUser(){
        User user = new User();
        userDao.save(user);
        return "posts/user-login";
    }

    @PostMapping("/user-login")
    public String addUser(@RequestParam(name="username") String username,
                          @RequestParam(name="email") String email,
                          @RequestParam(name="password")String password){
        User user = new User(username, email, password);
        userDao.save(user);
        return "redirect:/posts/index";
    }


    @GetMapping
    public String allPosts(Model model) {
        List <Post> allPosts = postDao.findAll();
//        User user = userDao.getById(1);
//        Post post = new Post();
//        post.setUser(user);
//        post.setTitle("Hi Everyone!");
//        post.setBody("How is everyone?");
//        postDao.save(post);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        if(postDao.findById(id) == null){
            return "redirect:/posts";
        }
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/post-form";
    }

    @PostMapping("/create")
    public String addPost(@ModelAttribute Post post){
        User user = userDao.getById(2L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String toEdit(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/edit-form";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post, @PathVariable long id) {
       User user = userDao.getById(2L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

}


