package com.cursedarcangel;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Scanner;
import java.util.HashMap;

public class CraftList {
    static String jsonFile = "recipes.json";
    public static void main(String[] args) {
        //initializing json and scanner
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("recipes.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> recipes = gson.fromJson(reader, Map.class);
        HashMap<String, Object> materialsPerItem = new HashMap<String, Object>();
        for (Map.Entry<?, ?> index : recipes.entrySet()) {
            materialsPerItem.put(String.valueOf(index.getKey()), index.getValue());
        }

        System.out.println("What do you want to craft?");
        String craft = scanner.nextLine();
        Object mats = materialsPerItem.get(craft);

        if (mats == null) {
            System.out.println("Sorry, we don't currently have that recipe. Try again! (No capital letters, no plural)");
        } else {
            System.out.println("How many do you want to craft?");
            int amount = scanner.nextInt();
            System.out.println(String.valueOf(mats));
            //cleaning up
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
