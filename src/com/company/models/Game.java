package com.company.models;

import com.apps.util.Console;
import com.apps.util.Prompter;
import java.util.Scanner;

public class Game {
    Prompter prompter = new Prompter(new Scanner(System.in));
    private final Player player;
    Home home = new Home();
    private String filepath = "resources/music.wav";
    private Music musicObject = new Music();

    public Game(Player player) {
        this.player = player;
    }

    public void playGame() {
        System.out.printf("\nWelcome to Mango Island, %s.", player.name);
        System.out.println();
        Console.pause(2000);
        System.out.println("You awaken on the beach in your modest shack on Mango Island after a long nap.\nYou look out the window and notice a sad traveler approaching you. You step outside to greet him.");
        System.out.println();

        Console.pause(2000);
        System.out.println("You can use the following commands to play the game: ");


        System.out.println("TIP: Enter TALK [name] to speak to others.\n");
        actions();
        System.out.println();
    }

    // menu actions for player
    public void actions(){

        while (true) {
//            Console.pause(1000);

            player.status();
            String userInput = prompter.prompt("\nCMD:  GO [direction] |  TALK [name]  |  GRAB [item]  |   DROP [item]" +
                    "    |     LOOK [item]" +

                    "  |  USE [item]    |   ATTACK [name]   |   MUSIC   |    QUIT \n" +
                    "------------------------------------------------------------------------------------------------" +
                    "---------------------------------------------------------\nYOUR MOVE: ").toLowerCase();
//            Console.pause(500);
            Console.clear();
            String[] inputSplit = userInput.trim().toLowerCase().split(" ");
            if(inputSplit[0].equals("look")) {
                player.look(inputSplit[1]);
            }
            else if (inputSplit[0].equals("go")) {
                if (inputSplit.length == 2) {
                    player.go(inputSplit[1]);
                }
                else if (inputSplit.length == 3) {
                    String island = inputSplit[1] + " " + inputSplit[2];
                    player.go(island);
                }
                else {
                    System.out.println("Invalid direction");
                }
            }
            else if (inputSplit[0].equals("talk")) {
                if (inputSplit.length == 2) {
                    player.talk(inputSplit[1]);
                }
                else if (inputSplit.length == 3) {
                    String npc = inputSplit[1] + " " + inputSplit[2];
                    player.talk(npc);
                }
                else {
                    System.out.println("Invalid name");
                }
            }
            else if (inputSplit[0].equals("grab")){
                if (inputSplit.length == 2) {
                    player.grabItem(inputSplit[1]);
                }
                else if (inputSplit.length == 3) {
                    String item = inputSplit[1] + " " + inputSplit[2];
                    player.grabItem(item);
                }
                else {
                    System.out.println("Invalid item");
                }
            }

            else if(inputSplit[0].equals("use")){
                player.useItem(inputSplit[1]);
            }
            else if (inputSplit[0].equals("drop")){
                if (inputSplit.length == 2) {
                    player.dropItem(inputSplit[1]);
                }
                else if (inputSplit.length == 3) {
                    String item = inputSplit[1] + " " + inputSplit[2];
                    player.dropItem(item);
                }
                else {
                    System.out.println("Invalid item");
                }
            }

            else if(inputSplit[0].equals("attack")){
                if (inputSplit.length == 2) {
                    player.attack(inputSplit[1]);
                }
                else if (inputSplit.length == 3) {
                    String character = inputSplit[1] + " " + inputSplit[2];
                    player.attack(character);
                }
            }
            else if(inputSplit[0].equals("music")){
                String musicInput = prompter.prompt("[PLAY] or [STOP] Music: ").toLowerCase();
                if (musicInput.equals("play")){
                    musicObject.playMusic(filepath);
                }
                else if (musicInput.equals("stop")){
                    musicObject.stopMusic(filepath);
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            else if(inputSplit[0].equals("quit")) {
                Home newHome = new Home();
                newHome.buildHome();
                break;
            }
            else {
                System.out.println("Invalid Command Entered");
            }
        }
    }

//    public void loadGame() {
//    }
//
//    public void saveGame() {
//    }
}
