package com.company.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.*;


public class Player {

    public String name;
    public int hp;
    public List<String> items;
    private JsonTools tools = new JsonTools();
    private String currentRoom = "Mango Jungle";
    Prompter prompter = new Prompter(new Scanner(System.in));

    public Player() {

    }

    public Player(String name, int hp, List<String> items) {
        this.name = name;
        this.hp = hp;
        this.items = items;
    }

    public void newPlayer() {
        System.out.println("\nNew Game Created");
        setPlayerName(prompter.prompt("\nAhoy, What is your name adventurer? "));
        System.out.println();
    }

    public void status() {
        String file = "location.json";
        Map<String, ArrayList> locationData = tools.readJson(file);
        ArrayList<Map<String, Object>> locations = locationData.get("locations");
        for (Map<String, Object> entry : locations) {
            if (entry.get("name").equals(currentRoom)) {
                Map<String, String> directions = (Map<String, String>) entry.get("directions");
                ArrayList<String> itemsHeld = (ArrayList<String>) entry.get("items");
                System.out.printf("Location: %s ", entry.get("name"));
                System.out.printf("\nDescription: %s ", entry.get("description"));
                directions.forEach((k,v) -> {
                    if (v.length() > 0) {
                        System.out.printf("\n%s: %s", k, v);
                    }
                });
                if (!itemsHeld.isEmpty()) {
                    System.out.printf("\nYou see: \n");
                    itemsHeld.forEach(e -> System.out.println(e));
                }
            }
        }
    }

    public void getItem(String item){

    }

    public void useItem(String item){

    }

    public void talk(String name){

    }

    public void look(String item){
        String file = "item.json";
        Map<String, ArrayList> itemData = tools.readJson(file);
        ArrayList<Map<String, String>> items = itemData.get("items");
        for (Map<String, String> entry : items) {
            if (entry.get("name").toLowerCase().equals(item)) {
                System.out.println(entry.get("description") + "\n");
            }
        }
    }

    private void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public List<String> getItems(Player player) {
        return player.items;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
}
