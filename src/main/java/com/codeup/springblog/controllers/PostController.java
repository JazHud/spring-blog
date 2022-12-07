package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/posts") /// <-- will at to the @GetMapping's below
public class PostController{

    private final PostRepository postDao;
    private final UserRepository userDao;

    private final EmailServices emailServices;

    public PostController(PostRepository postDao, UserRepository userDao, EmailServices emailServices){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailServices = emailServices;
    }

    @GetMapping("/user-login")
    public String createUser(){
        User user = new User();
        userDao.save(user);
        return "posts/loginPage";
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

//    To search by one work in a post
//    @GetMapping("/search")
    @GetMapping("/{username}/search")
    public String findUsername(Model model){
        User searchedUser = new User();
        searchedUser = userDao.findByUsername(searchedUser.getUsername());
        model.addAttribute("searchedUser", searchedUser);
            return "redirect:/posts/search-form";
    }

    @PostMapping("/{username}/search")
    public String foundUsername(@ModelAttribute User user) {
        if (userDao.findByUsername(user.getUsername()) == null) {
            return "redirect:/posts";
        }
        User foundUser = new User();
        userDao.findByUsername(foundUser.getUsername());
        userDao.save(user);
        return "redirect:/posts/search-form";
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
//        the line below can access the logged in user from a controller (aka User controller) and from there it has access to the User object.
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postDao.save(post);
        emailServices.prepareAndSend(loggedInUser, post.getTitle(), post.getBody());
        return "redirect:";
    }

    @GetMapping("/{id}/edit")
    public String toEdit(@PathVariable long id, Model model){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (currentUserId == 0){
            return "redirect:/user/registration";
        }
        Post post = postDao.findById(id);
        if(post.getUser().getId() != currentUserId){
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "/posts/edit-form";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post) {
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (currentUserId == 0){
            return "redirect:/login";
        }
        User user = userDao.findById(currentUserId);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String toDelete(@PathVariable long id){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//        if (currentUserId == 0){
//            return "redirect:/user/registration";
//        }
        Post post = postDao.findById(id);
        if(post.getUser().getId() == currentUserId) {
            postDao.delete(post);
        }
        return "redirect:/posts";
    }

//    @PostMapping("/{id}/delete")
//    public String deletePost(@ModelAttribute Post post){
//        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//        if (currentUserId == 0){
//            return "redirect:/login";
//        }
//        postDao.delete(post);
//            return "redirect:/posts";
//    }

}


