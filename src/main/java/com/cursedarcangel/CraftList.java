package com.cursedarcangel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.Gson;
import java.util.Scanner;
import java.util.HashMap;

public class CraftList {
    static String jsonFile = "recipes.json";
    public static void main(String[] args) {
        //initializing json and scanner
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        Reader reader = null;
        InputStream input = CraftList.class.getResourceAsStream("/recipes.json");
        reader = new InputStreamReader(input);
        Map<String, Object > recipes = gson.fromJson(reader, Map.class);
        HashMap<String, Object> materialsPerItem = new HashMap<>();
        for (Map.Entry<String, Object > index : recipes.entrySet()) {
            materialsPerItem.put(String.valueOf(index.getKey()), index.getValue());
        }

        System.out.println("What do you want to craft?");
        String craft = scanner.nextLine();
        Map<String, Double> mats = (Map<String, Double>) materialsPerItem.get(craft);
        int counter = 0;

        if (mats == null) {
            System.out.println("Sorry, we don't currently have that recipe. Try again! (No capital letters, no plural)");
        } else {
            System.out.println("How many do you want to craft?");
            int amount = scanner.nextInt();
            for (Map.Entry<String, Double> index : mats.entrySet()) {
                System.out.println(index.getKey() + ":" + Math.round(index.getValue() * amount));
            }
            //cleaning up
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
