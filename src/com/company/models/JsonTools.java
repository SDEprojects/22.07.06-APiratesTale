package com.company.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonTools {


    public void readJson() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("./src/com/company/data/item.json"));
            Map<String, String> items = gson.fromJson(reader, Map.class);
            System.out.println(items.get("items").toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson() {
    }

}