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
    public List<String> inventory = new ArrayList<>();
    private JsonTools tools = new JsonTools();
    private String currentRoom = "Beach Shack";
    private Map<String, String> directions;
    private ArrayList<String> locationItems;
    Prompter prompter = new Prompter(new Scanner(System.in));

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
        String file = "location.json";
        ArrayList<Map<String, Object>> locationData = tools.readJson(file);
        for (Map<String, Object> entry : locationData) {
            if (entry.get("name").equals(currentRoom)) {
                directions = (Map<String, String>) entry.get("directions");
                locationItems = (ArrayList<String>) entry.get("items");
                System.out.printf("Location: %s ", entry.get("name"));
                System.out.printf("\nDescription: %s ", entry.get("description"));
                System.out.println("\nDirections: ");
                directions.forEach((k,v) -> {
                    if (v.length() > 0) {
                        System.out.printf("%s: %s\n", k, v);
                    }
                });
                if (!locationItems.isEmpty()) {
                    System.out.printf("\nYou see: \n");
                    locationItems.forEach(e -> System.out.println(e));
                }
            }
        }
    }

    public void grabItem(String item){
        if(locationItems.contains(item)) {
            System.out.println("user input: "+ item);
            //remove from the location
            locationItems.remove(item);
            System.out.println("removed item: " + locationItems);
            //add to inventory
            inventory.add(item);
            System.out.println("added item inventory:" + inventory);
            this.locationItems = locationItems;
        }
    }

    public void useItem(String item){

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
