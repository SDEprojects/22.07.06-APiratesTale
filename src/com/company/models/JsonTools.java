package com.company.models;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class JsonTools {


    public ArrayList<Map<String, Object>> readJson(String file) {
        try {
            Gson gson = new Gson();
            String path = String.format("resources/%s", file);
            Reader reader = Files.newBufferedReader(Paths.get(path));
            ArrayList<Map<String, Object>> data = gson.fromJson(reader, ArrayList.class);
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeJson(String file, String fileKey, Map<String, Object> entry) {
        // Get json data
        ArrayList<Map<String, Object>> data = readJson(file);
        // add to json object
        data.add(entry);
        try (FileWriter overWrite = new FileWriter(file)) {
            //We can write any JSONArray or JSONObject instance to the file
            overWrite.write(String.valueOf(data));
            overWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}