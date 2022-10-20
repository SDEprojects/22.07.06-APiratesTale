package com.company;

import com.company.models.Home;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

import com.apps.util.Prompter;
import com.company.models.Player;


public class Main {

    public static void main(String[] args) {
        Home home = new Home();
        home.buildHome();
        Player player = new Player(home.playerName, 10, new ArrayList<>());

        System.out.printf("Welcome to Mango Island %s", player.name);
    }
}
