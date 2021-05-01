package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;

import java.util.ArrayList;

public class seanInsert {
    public static ArrayList<Athlete> insert (ArrayList<Athlete> arr) {
        arr.add(0, new Athlete(100, 200000, "Swim")); //head insert
        arr.add(2, new Athlete(100, 200000, "Track")); //mid insert
        arr.add(new Athlete(12, 1000, "Dive")); //tail insert
        return arr;
    }

    public static java.util.LinkedList<Athlete> insert(java.util.LinkedList<Athlete> arr) {
        arr.add(0, new Athlete(100, 200000, "Swim")); //head insert
        arr.add(2, new Athlete(100, 200000, "Track")); //mid insert
        arr.add(new Athlete(12, 1000, "Dive")); //tail insert
        return arr;
    }

}
