package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rollDice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollGuess(@PathVariable String n, Model model) {
        int diceRoll = (int) Math.floor(Math.random() * 6) + 1; //cast into an int to remove decimal point
        model.addAttribute("n", n);
        model.addAttribute("diceRoll", diceRoll);
        return "rollDice";
    }
}
