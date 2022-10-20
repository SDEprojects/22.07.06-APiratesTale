package com.company.models;

import com.apps.util.Prompter;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    Prompter prompter = new Prompter(new Scanner(System.in));
    private final Player player;

    public Game(Player player) {
        this.player = player;
    }
    public void playGame(){
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Welcome to Mango Island, %s.", player.name);
        System.out.println();
        System.out.println("“You awaken on the beach in your modest shack on Mango Island after a long nap. You look out the window and notice a sad traveler approaching you. You step outside to greet him.”");
        System.out.println();
        System.out.println("You can use the following commands to play the game: ");
        System.out.println("TIP: Enter TALK [name] to speak to others.");
        actions();
        System.out.println();

    }

    public void actions(){
        prompter.prompt("CMD:  GO [direction] |  TALK [name]  |  GET [item]  |  LOOK [item]  |  USE [item]  | ");
    }

    public void loadGame() {

    }

    public void saveGame() {

    }

}