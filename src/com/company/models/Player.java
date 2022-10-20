package com.company.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Player {

    public String name;
    public int hp;
    public List<String> items;
    private JsonTools tools = new JsonTools();

    public Player(String name, int hp, List<String> items) {
        this.name = name;
        this.hp = hp;
        this.items = items;
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

    public List<String> getItems(Player player) {
        return player.items;
    }
}
