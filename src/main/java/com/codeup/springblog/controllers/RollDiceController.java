package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rollDice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollGuess(@PathVariable int n, Model model){
        model.addAttribute("n", n);
        double rand = (Math.random() + 1);
        return "rollDice";

        }

}
