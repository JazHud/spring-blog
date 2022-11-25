package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.models.Supplier;
import com.codeup.springblog.repositories.CoffeeRepository;
import com.codeup.springblog.repositories.SupplierRepository;
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

    private final SupplierRepository supplierDao;


    public CoffeeController(CoffeeRepository coffeeDao, SupplierRepository supplierDao){
        this.coffeeDao = coffeeDao;
        this.supplierDao = supplierDao;
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

//    public String addCoffeeForm(Model model){
//
//        return "all-coffees";
//    }

    @GetMapping("/new")
    public String addCoffeeForm(Model model){
        List<Supplier> suppliers = supplierDao.findAll();
        model.addAttribute("suppliers", suppliers);
        return "create-coffee";
    }

    @PostMapping("/new")
    public String addCoffee(@RequestParam(name="roast") String roast, @RequestParam(name="origin") String origin, @RequestParam(name="brand") String brand, long id){
        Supplier supplier = supplierDao.findById(id);
        Coffee coffee = new Coffee(roast, origin, brand, supplier);
        coffeeDao.save(coffee);
        return "redirect:/coffee/all-coffees";
    }

    @PostMapping
    public String signUp(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        return "coffee";
    }

    @GetMapping("/suppliers")
    public String showSuppliersForm(Model model){
        List<Supplier> suppliers = supplierDao.findAll();
        model.addAttribute("suppliers", suppliers);
        return "/suppliers";
    }

    @PostMapping("/suppliers")
    public String insertSupplier(@RequestParam(name="name") String name){
        Supplier supplier = new Supplier(name);
        supplierDao.save(supplier);
        return "redirect:/coffee/suppliers";
    }
}
