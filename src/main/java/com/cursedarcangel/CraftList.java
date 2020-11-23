package com.cursedarcangel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import com.google.gson.Gson;
import java.util.Scanner;
import java.util.HashMap;

public class CraftList {
    public static String parseJson(String craft, Integer amount) {
        String output = "<html>";
        String recipeNotFound = "<html>";
        Gson gson = new Gson();
        Reader reader = null;
        InputStream input = CraftList.class.getResourceAsStream("/recipes.json");
        reader = new InputStreamReader(input);
        Map<String, Object > recipes = gson.fromJson(reader, Map.class);
        HashMap<String, Object> materialsPerItem = new HashMap<>();
        for (Map.Entry<String, Object > index : recipes.entrySet()) {
            materialsPerItem.put(String.valueOf(index.getKey()), index.getValue());
        }

        Map<String, Double> mats = (Map<String, Double>) materialsPerItem.get(craft);

        if (mats == null) {
            recipeNotFound += "Sorry, we don't <br>currently have that recipe. Try again! <br>(No capital letters, <br>no plural)";
            return recipeNotFound + "</html>";
        } else if (amount == 0 || amount == null) {
            System.out.println("Put in how many you want to craft");
        } else {
            for (Map.Entry<String, Double> index : mats.entrySet()) {
                // System.out.println(index.getKey() + ":" + Math.round(index.getValue() * amount));
                output += index.getKey() + ":" + String.valueOf(Math.round(index.getValue() * amount)) + "<br>";
            }
            //cleaning up
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output + "</html>";
    }
}