package com.cursedarcangel;

import java.util.HashMap;

public class Recipe {
    public String name;
    public HashMap<String, Integer> materialList = new HashMap<String, Integer>();

    public void name(String name) {
        this.name = name;
    }
    public void hashTest(String otherThing, int otherNum) {
        materialList.put(name, 1);
        materialList.put(otherThing, otherNum);
        for (String i : materialList.keySet()) {
            System.out.println(i + " " + materialList.get(i));
        }
    }
}
