package com.company.models;

import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.*;

public class Home {

    //Fields
    private String playerName;
    Prompter prompter =  new Prompter(new Scanner(System.in));

//Methods

public void buildHome(){
   banner();
   gameInfo();
   startGame();
}
private void banner() {
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

    private void startGame(){
    setMenuSelection(prompter.prompt("MENU:   New Game  |  Exit"));
}

    private void setMenuSelection(String menuSelection) {
    if(menuSelection.toLowerCase() == "new game"){
          //ToDo: Method to create new game

    }
    if(menuSelection.toLowerCase() == "exit"){
         System.exit(0);
    }
    }
}
