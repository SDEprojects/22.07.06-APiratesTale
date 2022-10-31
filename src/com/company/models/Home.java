package com.company.models;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;

public class Home {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    Prompter prompter = new Prompter(new Scanner(System.in));



    //Methods
    public void buildHome() {
        banner();
        gameInfo();
        startGame();
    }

    // welcome screen
    private void banner() {
        try {
            List<String> textLines = Files.readAllLines(Paths.get("resources/welcome.text"));
            for(String line : textLines){
                Thread.sleep(500);
                System.out.println( ANSI_YELLOW + line + ANSI_RESET);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void gameInfo()  {

        System.out.println(ANSI_BLUE +"This is a terminal based role playing game. Seek adventure, by solving the mystery of the " +
                            "secret treasure of Skull Island. Please select a MENU option to continue.\n" + ANSI_RESET);
    }

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
