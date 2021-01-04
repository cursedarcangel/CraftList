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
        Scanner scanner = new Scanner(System.in);
	
	//what do they want to craft
        System.out.println("What do you want to craft?");
        String craft = scanner.nextLine();
	
	//how many do they want to craft
        System.out.println("How many do you want to craft?");
        int amount = scanner.nextInt();

	//finding the amount of materials
        parseJson(craft, amount);
    }
    public static void parseJson(String craft, int amount) {

	//initializing things
        Gson gson = new Gson();
        Reader reader = null;

	//reading the json file of recipes
        InputStream input = CraftList.class.getResourceAsStream("/recipes.json");
        reader = new InputStreamReader(input);

	//getting the recipes from the json
        Map<String, Object > recipes = gson.fromJson(reader, Map.class);

	//getting the materials for one item
        HashMap<String, Object> materialsPerItem = new HashMap<>();
        for (Map.Entry<String, Object > index : recipes.entrySet()) {
            materialsPerItem.put(String.valueOf(index.getKey()), index.getValue());
        }
	
	//this is what we return, the how many materials for how many items they want to craft
        Map<String, Double> mats = (Map<String, Double>) materialsPerItem.get(craft);

	//the logic
	//if we dont have the recipe, break
	//else do the calculation and return the results
        if (mats == null) {
            System.out.println("Sorry, we don't currently have that recipe. Try again! (No capital letters, no plural)");
        } else {
	    //calculating the number of stacks and remainder, returning it
            for (Map.Entry<String, Double> index : mats.entrySet()) {
                int numOfMats = (int) Math.round(index.getValue() * amount);
                int stacks = numOfMats / 64;
                int remainder = (numOfMats % 64);
                System.out.println(index.getKey() + " : " + "(" + String.valueOf(numOfMats) + ") " + String.valueOf(stacks) + " stack(s) and " + String.valueOf(remainder) + " items");
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
