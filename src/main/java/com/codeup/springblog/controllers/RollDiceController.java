package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    @ResponseBody
    public String intro(){
        return "Guess a number 1 through 6";
    }

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public int rollGuess(@PathVariable int n){
//        double rand = (Math.random() + 1),
        return n;

        }

}
