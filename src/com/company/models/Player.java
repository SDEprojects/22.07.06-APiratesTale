package com.company.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.apps.util.Prompter;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Stream;


public class Player {
    public String name;
    public int hp = 10;
    public int dp = 1;
    public List<String> inventory = new ArrayList<>();
    private JsonTools tools = new JsonTools();
    private String currentRoom = "Beach Shack";
    private Map<String, String> directions;
    private ArrayList<String> locationItems;
    private ArrayList<String> locationNPC;
    Prompter prompter = new Prompter(new Scanner(System.in));
    ArrayList<Map<String, Object>> locationData = tools.readJson("location.json");
    ArrayList<Map<String, Object>> characterData = tools.readJson("character.json");

    public Player() {
    }

    public Player(String name, int hp, int dp, List<String> inventory) {
        this.name = name;
        this.hp = hp;
        this.dp = dp;
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
                directions.forEach((k, v) -> {
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
                System.out.printf("HP: %s     Damage Points: %s      Inventory: %s ", hp, dp, inventory);
            }

        }
    }

    public void grabItem(String item) {
        if (locationItems.contains(item)) {
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

    public void useItem(String item) {
        String file = "item.json";
        ArrayList<Map<String, Object>> itemData = tools.readJson(file);
        for (Map<String, Object> entry : itemData) {
            if (inventory.contains(item) && entry.get("name").toString().toLowerCase().equals(item)) {
                System.out.println(entry.get("description") + "\n");
                if (item.equals("mango")) {
                    hp += 5;
                } else if (item.equals("banana")) {
                    hp += 10;
                } else if (item.equals("sword")) {
                    System.out.println("In order to wield the sword, please enter 'ATTACK' [name]");
                }

                // TODO: subtract used items from inventory
            }

        }

    }

    public void talk(String name) {

        for (Map<String, Object> entry : characterData) {
            if (entry.get("name").equals(name)) {
                while (true) {
                    System.out.println("Speaking to: " + entry.get("name"));
                    Map<String, String> dialogue = (Map<String, String>) entry.get("quote");
                    System.out.println(dialogue.get("initial"));


                    if (dialogue.containsKey("quest")) {
                        if (inventory.contains(entry.get("questReq"))) {
                            System.out.println(dialogue.get("reward"));
                            if (entry.containsKey("rewards")) {
                                ArrayList<String> rewardsArray = (ArrayList<String>) entry.get("items");
                                for (String reward : rewardsArray) {
                                    inventory.add(reward);
                                    System.out.println(reward + " was added to inventory.\n");
                                }
                                entry.remove("reward");
                            }
                        } else {
                            System.out.println(dialogue.get("quest"));
                            if (dialogue.containsKey("yes")) {
                                String userInput = prompter.prompt("");
                                if (userInput.equals("yes")) {
                                    System.out.println(dialogue.get("yes"));
                                    if (entry.containsKey("items")) {
                                        ArrayList<String> itemsArray = (ArrayList<String>) entry.get("items");
                                        for (String item : itemsArray) {
                                            inventory.add(item);
                                            System.out.println(item + " was added to inventory.\n");
                                        }
                                        entry.remove("items");
                                    }
                                }
                                if (userInput.equals("no")) {
                                    System.out.println(dialogue.get("no"));
                                    break;
                                }
                            }
                        }
                    } else if (entry.containsKey("items")) {
                        ArrayList<String> itemsArray = (ArrayList<String>) entry.get("items");
                        for (String item : itemsArray) {
                            inventory.add(item);
                            System.out.println(item + " was added to inventory.\n");
                        }
                        entry.remove("items");
                    }
                    break;
                }
            }
        }
    }

    public void go(String directionInput) {
        String location = directions.get(directionInput);
        if (!location.equals("Boat") && !location.equals("Monkey Temple")) {
            currentRoom = location;
        } else if (inventory.contains("boat pass") && location.equals("Boat")) {
            currentRoom = location;
        } else if (inventory.contains("temple pass") && location.equals("Monkey Temple")) {
            currentRoom = location;
        }

    }

    public void look(String item) {
        String file = "item.json";
        ArrayList<Map<String, Object>> itemData = tools.readJson(file);
        for (Map<String, Object> entry : itemData) {
            if (entry.get("name").toString().toLowerCase().equals(item)) {
                System.out.println(entry.get("description") + "\n");
            }
        }
    }

    public void attack(String c) {
        for (Map<String, Object> entry : characterData) {
            if (entry.get("name").equals(c)) {
                while (true) {
                    System.out.println(entry.get("name") + "'s current hp is : " + entry.get("hp"));
                    System.out.println("You are attacking: " + entry.get("name"));
                    Double points = (Double) entry.get("hp");
                    if (inventory.contains("sword")) {
                        dp = 5;
                    } else {
                        dp = dp;
                    }
                    points -= dp;
                    entry.put("hp", points);
                    System.out.println(entry.get("name") + "'s hp after attack is : " + points);

                    if (points <= 0 && entry.containsKey("items")) {
                        System.out.println(name + " has wasted " + entry.get("name") + "!");
                        ArrayList<String> itemsArray = (ArrayList<String>) entry.get("items");
                        for (String item : itemsArray) {
                            inventory.add(item);
                            System.out.println(entry.get("name") + "'s " + item + " has been added to your inventory");
                        }
                    }
                    break;
                }

                // TODO: Fix input validation for incorrect name

            } else {
                System.out.println("Invalid name");
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
