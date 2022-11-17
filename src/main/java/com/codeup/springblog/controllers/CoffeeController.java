package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.repositories.CoffeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

//    DAO = data access object
    private final CoffeeRepository coffeeDao;

    public CoffeeController(CoffeeRepository coffeeDao){
        this.coffeeDao = coffeeDao;
    }

    @GetMapping
    public String coffee(){
        return "coffee"; //<-- a reference to a file in the templates directory
    }

    @GetMapping("/{roast}")
    public String roast(@PathVariable String roast, Model model){
        Coffee selection = new Coffee(roast, "Cool Bears");
        Coffee selection2 = new Coffee(roast, "Coffee Bros");
//        selection.setRoast(roast);
        selection.setOrigin("Ethiopia");
        selection.setOrigin("Vietnam");
        List<Coffee> selections = new ArrayList<>(List.of(selection, selection2));
        model.addAttribute("selections", selections);
        return "coffee";
    }
    //model is an object to grab the information of which type of roast

    @GetMapping("/all-coffees")
    public String allCoffeess(Model model){  //(Model, name of model) aka thymeleaf
        List<Coffee> coffees = coffeeDao.findAll(); // is a select * query and will display it as a list
        model.addAttribute("coffees", coffees);
        return "all-coffees";
    }

    @GetMapping("/new")
    public String addCoffeeForm(){
        return "create-coffee";
    }

    @PostMapping("/new")
    public String addCoffee(@RequestParam(name="roast") String roast, @RequestParam(name="origin") String origin, @RequestParam(name="brand") String brand){
        Coffee coffee = new Coffee(roast, origin, brand);
        coffeeDao.save(coffee);
        return "redirect:all-coffees";
    }

    @PostMapping
    public String signUp(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        return "coffee";
    }
}
