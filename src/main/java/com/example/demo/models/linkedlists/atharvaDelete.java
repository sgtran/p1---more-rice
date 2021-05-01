package com.example.demo.models.linkedlists;
import minilabz.Richpeople;
import java.util.ArrayList;

public class atharvaDelete {

    public static ArrayList<Richpeople> delete (ArrayList<Richpeople> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }

    public static java.util.LinkedList<Richpeople> delete(java.util.LinkedList<Richpeople> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }
}
