package com.company.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class JsonTools {


    public Map<String, ArrayList> readJson(String file) {
        try {
            Gson gson = new Gson();
            String path = String.format("./data/%s",file);
            Reader reader = Files.newBufferedReader(Paths.get(path));
            Map<String, ArrayList> data = gson.fromJson(reader, Map.class);
//            ArrayList<Map<String, String>> items = itemsData.get("items");
//            for (Map<String, String> entry : items) {
//                if (entry.get("name").equals("Map")) {
//                    System.out.println(entry.get("description"));
//                }
//            }
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeJson() {
    }

}