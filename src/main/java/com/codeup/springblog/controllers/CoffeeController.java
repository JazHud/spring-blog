package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.models.Customer;
import com.codeup.springblog.models.Supplier;
import com.codeup.springblog.repositories.CoffeeRepository;
import com.codeup.springblog.repositories.CustomerRepository;
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

    private final CustomerRepository customerDao;


    public CoffeeController(CoffeeRepository coffeeDao, SupplierRepository supplierDao, CustomerRepository customerDao){
        this.coffeeDao = coffeeDao;
        this.supplierDao = supplierDao;
        this.customerDao = customerDao;
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
        model.addAttribute("coffee", new Coffee());
        return "create-coffee";
    }

    @PostMapping("/new")
    public String addCoffee(@ModelAttribute Coffee coffee){
//        Supplier supplier = supplierDao.findById(id);
//        Coffee coffee = new Coffee(roast, origin, brand, supplier);
        coffeeDao.save(coffee);
        return "redirect:/coffee/all-coffees";
    }


//    @PostMapping("/new")
//    public String addCoffee(@RequestParam(name="roast") String roast, @RequestParam(name="origin") String origin, @RequestParam(name="brand") String brand, long id){
//        Supplier supplier = supplierDao.findById(id);
//        Coffee coffee = new Coffee(roast, origin, brand, supplier);
//        coffeeDao.save(coffee);
//        return "redirect:/coffee/all-coffees";
//    }

    @PostMapping
    public String signUp(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        return "coffee";
    }

    @GetMapping("/suppliers")
    public String showSuppliersForm(Model model){
        List<Supplier> suppliers = supplierDao.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("supplier", new Supplier());
        return "/suppliers";
    }

    @PostMapping("/suppliers")
    public String insertSupplier(@ModelAttribute Supplier supplier){
        supplierDao.save(supplier);
        return "redirect:/coffee/suppliers";
    }


    @GetMapping("/register")
    public String showReistrationForm(Model model){
        model.addAttribute("customer", new Customer());
        return "/registration";
    }

    //added Model to get rid of @RequestParam and add th:object and th:fields to bind them together
    @PostMapping("/customer/new")
    public String addCustomer(@ModelAttribute Customer customer){
        customerDao.save(customer);
        return "redirect:/coffee";
    }

//    @PostMapping("/customer/new")
//    public String addCustomer(@RequestParam(name="name") String name,
//                              @RequestParam(name="email") String email){
//        customerDao.save(new Customer(name, email));
//        return "redirect:/coffee/all-coffees";
//    }

//    The below code allows us to input specific data into customers_coffees table via id JQuery needed to allow user to click on favorite pull the hard coded customer id(in the html) and the coffee id data via th:data-(data-name) in all-coffees.html
    @PostMapping("/customer/{customerId}/favorite/{coffeeId}")
    public String favoriteCoffee(@PathVariable long customerId, @PathVariable long coffeeId){
        Customer customer = customerDao.findById(customerId);
        List<Coffee> favorites = customer.getCoffeeList();
        favorites.add(coffeeDao.findById(coffeeId));
        customer.setCoffeeList(favorites);
        customerDao.save(customer);
        return "redirect:/coffee";
    }
}
