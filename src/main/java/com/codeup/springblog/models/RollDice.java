package com.codeup.springblog.models;

public class RollDice {

    public static double generateRandomNumber(){
        return Math.floor(Math.random()*(6 - 1 + 1) + 1);
    }

    public static void main(String[] args) {
        System.out.println(generateRandomNumber());
    }
}


