package com.company.models;

import java.util.List;


public class Player {

    public String name;
    public int hp;
    public List<String> items;

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

    }

    public List<String> getItems(Player player) {
        return player.items;
    }
}
