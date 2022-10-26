package com.company.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.*;


public class Player {
    public String name;
    public int hp = 10;
    public List<String> inventory = new ArrayList<>();
    private JsonTools tools = new JsonTools();
    private String currentRoom = "Beach Shack";
    private Map<String, String> directions;
    private ArrayList<String> locationItems;
    private ArrayList<String> locationNPC;
    Prompter prompter = new Prompter(new Scanner(System.in));
    String file = "location.json";
    ArrayList<Map<String, Object>> locationData = tools.readJson(file);

    public Player() {
    }

    public Player(String name, int hp, List<String> inventory) {
        this.name = name;
        this.hp = hp;
        this.inventory = inventory;
    }

    public void newPlayer() {
        System.out.println("\nNew Game Created");
        setPlayerName(prompter.prompt("\nAhoy, What is your name adventurer? "));
        System.out.println();
    }

    public void status() {
        for (Map<String, Object> entry : locationData) {
            if (entry.get("name").equals(currentRoom)) {
                directions = (Map<String, String>) entry.get("directions");
                locationItems = (ArrayList<String>) entry.get("items");
                locationNPC = (ArrayList<String>) entry.get("NPC");
                System.out.printf("Location: %s ", entry.get("name"));
                System.out.printf("\nDescription: %s ", entry.get("description"));
                System.out.println("\nDirections: ");
                directions.forEach((k,v) -> {
                    if (v.length() > 0) {
                        System.out.printf("%s: %s\n", k, v);
                    }
                });
                if (!locationNPC.isEmpty()) {
                    System.out.printf("\nCharacters: \n");
                    locationNPC.forEach(e -> System.out.println(e));
                }
                if (!locationItems.isEmpty()) {
                    System.out.printf("\nYou see: \n");
                    locationItems.forEach(e -> System.out.println(e));
                }
                System.out.printf("Inventory: %s ", inventory);
                }

        }
    }

    public void grabItem(String item){
        if(locationItems.contains(item)) {
            System.out.println("user input: " + item);
            //remove from the location
            locationItems.remove(item);
            System.out.println("items available at location " + locationItems);
            //add to inventory
            inventory.add(item);
            System.out.println("current inventory: " + inventory);
            this.locationItems = locationItems;
        }
    }

    public void useItem(String item){
        String file = "item.json";
        ArrayList<Map<String, Object>> itemData = tools.readJson(file);
        for (Map<String, Object> entry : itemData) {
            if(inventory.contains(item) && entry.get("name").toString().toLowerCase().equals(item)) {
                System.out.println(entry.get("description") + "\n");
//                hp = hp + (entry.get("value"));
//                hp = hp + (entry.get("value"));
        }


        }

    }

    public void talk(String name){

    }

    public void go(String directionInput) {
        currentRoom = directions.get(directionInput);
    }

    public void look(String item){
        String file = "item.json";
        ArrayList<Map<String, Object>> itemData = tools.readJson(file);
        for (Map<String, Object> entry : itemData) {
            if (entry.get("name").toString().toLowerCase().equals(item)) {
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
        return player.inventory;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
}
