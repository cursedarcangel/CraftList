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
	//this is calculating the materials for the gui version of CraftList
	
	//initializing variables
        String output = "<html>";
        String recipeNotFound = "<html>";
        Gson gson = new Gson();
        Reader reader = null;

	//getting the recipes
        InputStream input = CraftList.class.getResourceAsStream("/recipes.json");
        reader = new InputStreamReader(input);

	//putting the recipes in a hashmap to be able to call them easier
        Map<String, Object > recipes = gson.fromJson(reader, Map.class);
        HashMap<String, Object> materialsPerItem = new HashMap<>();

	//adding the materials for one craft to a map
        for (Map.Entry<String, Object > index : recipes.entrySet()) {
            materialsPerItem.put(String.valueOf(index.getKey()), index.getValue());
        }
	
	//this is what we return
        Map<String, Double> mats = (Map<String, Double>) materialsPerItem.get(craft);
	
	//the logic
	//if we dont have the recipe, tell the user
	//else, do the calculations and return ther result
        if (mats == null) {
            recipeNotFound += "Sorry, we don't <br>currently have that recipe. Try again! <br>(No capital letters, <br>no plural)";
            return recipeNotFound + "</html>";
        } else if (amount == 0 || amount == null) {
            System.out.println("Put in how many you want to craft");
        } else {

	    //looping through the materials for one craft and doing the calculations for how many times the user wants to craft
            for (Map.Entry<String, Double> index : mats.entrySet()) {
                int numOfMats = (int) Math.round(index.getValue() * amount);
                int stacks = numOfMats / 64;
                int remainder = (numOfMats % 64);
                output += index.getKey() + " : " + "(" + numOfMats + ") " + String.valueOf(stacks) + " stack(s) and" + " " + String.valueOf(remainder) + " items" + "<br>";
            }
            //cleaning up by closing the reader
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	
	//we have to use some html things because if we dont the text returned doesnt look correct
        return output + "</html>";
    }
}
