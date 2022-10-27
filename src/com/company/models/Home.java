package com.company.models;

import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.*;

public class Home {

    //Fields
    private String filepath = "./src/resources/music.wav";
    private Music musicObject = new Music();

    Prompter prompter = new Prompter(new Scanner(System.in));

    //Methods
    public void buildHome() {

        banner();
        gameInfo();
        startGame();
    }

    private void banner() {
        musicObject.playMusic(filepath);
        System.out.println("\n" +
                "\n" +
                " _______    _______ _________ _______  _______ _________ _______  _  _______   _________ _______  _        _______ \n" +
                "(  ___  )  (  ____ )\\__   __/(  ____ )(  ___  )\\__   __/(  ____ \\( )(  ____ \\  \\__   __/(  ___  )( \\      (  ____ \\\n" +
                "| (   ) |  | (    )|   ) (   | (    )|| (   ) |   ) (   | (    \\/|/ | (    \\/     ) (   | (   ) || (      | (    \\/\n" +
                "| (___) |  | (____)|   | |   | (____)|| (___) |   | |   | (__       | (_____      | |   | (___) || |      | (__    \n" +
                "|  ___  |  |  _____)   | |   |     __)|  ___  |   | |   |  __)      (_____  )     | |   |  ___  || |      |  __)   \n" +
                "| (   ) |  | (         | |   | (\\ (   | (   ) |   | |   | (               ) |     | |   | (   ) || |      | (      \n" +
                "| )   ( |  | )      ___) (___| ) \\ \\__| )   ( |   | |   | (____/\\   /\\____) |     | |   | )   ( || (____/\\| (____/\\\n" +
                "|/     \\|  |/       \\_______/|/   \\__/|/     \\|   )_(   (_______/   \\_______)     )_(   |/     \\|(_______/(_______/\n" +
                "                                                                                                                   \n" +
                "\n");
    }

    private void gameInfo() {
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
