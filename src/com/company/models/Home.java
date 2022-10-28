package com.company.models;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;

public class Home {


    Prompter prompter = new Prompter(new Scanner(System.in));


    //Methods
    public void buildHome() {
        banner();
        gameInfo();
        startGame();
    }

    private void banner() {
        try {
            List<String> textLines = Files.readAllLines(Paths.get("./src/resources/welcome.text"));
            for(String line : textLines){
                Thread.sleep(500);
                System.out.println( line );
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    private void gameInfo()  {

        System.out.println("This is a terminal based role playing game. Seek adventure, by solving the mystery of the " +
                        "secret treasure of Skull Island. Please select a MENU option to continue.\n");


    }

//    private void startGame() {
//
//            setMenuSelection(prompter.prompt("MENU:   New Game  |  Exit \n"));
//    }

    private void startGame() {
        while (true) {
            String menuSelection = prompter.prompt("MENU:   New Game  |  Exit \n");
            if (menuSelection.toLowerCase().equals("new game")) {
                Player player = new Player();
                player.newPlayer();
                Game newGame = new Game(player);
                newGame.playGame();
                break;
            }
            if (menuSelection.toLowerCase().equals("exit")) {
                System.out.println("\nThanks For Playing... Good Bye!");
                System.exit(0);
            }
            else {
                System.out.println("Invalid Command");
            }
        }

    }


}
