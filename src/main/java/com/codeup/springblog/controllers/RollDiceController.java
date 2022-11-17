package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RollDiceController {

    @RequestMapping("/roll-dice")
    public String rollDice(){
        return "rollDice";
    }

    @GetMapping("/{n}")
    public int rollGuess(@PathVariable int n){
//        double rand = (Math.random() + 1),
        return n;

        }

}
