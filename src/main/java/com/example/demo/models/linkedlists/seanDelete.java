package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;

import java.util.ArrayList;

public class seanDelete {
    public static ArrayList<Athlete> delete (ArrayList<Athlete> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }

    public static java.util.LinkedList<Athlete> delete(java.util.LinkedList<Athlete> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }


}
