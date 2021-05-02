package com.example.demo.models.linkedlists;

import minilabz.Water;

import java.util.ArrayList;

public class KevinInsert {
    public static ArrayList<Water> insert (ArrayList<Water> arr) {
        arr.add(0, new Water(10, 600, "Dasani"));
        arr.add(2, new Water(20, 1000, "Kirkland"));
        arr.add(new Water(12, 1200, "Aquafina"));
        return arr;
    }

    public static java.util.LinkedList<Water> insert(java.util.LinkedList<Water> arr) {
        arr.add(0, new Water(100, 200000, "Swim"));
        arr.add(2, new Water(100, 200000, "Track"));
        arr.add(new Water(12, 1000, "Dive"));
        return arr;
    }

}