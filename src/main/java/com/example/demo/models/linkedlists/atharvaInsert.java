package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;

import java.util.ArrayList;

public class atharvaInsert {
    public static ArrayList<Richpeople> insert (ArrayList<Richpeople> arr) {
        arr.add(0, new Richpeople(52, 123456, "Apple")); //head insert
        arr.add(2, new Richpeople(52, 123456, "Apple")); //mid insert
        arr.add(new Richpeople(52, 123456, "Apple")); //tail insert
        return arr;
    }

    public static java.util.LinkedList<Richpeople> insert(java.util.LinkedList<Richpeople> arr) {
        arr.add(0, new Richpeople(52, 123456, "Apple")); //head insert
        arr.add(2, new Richpeople(52, 123456, "Apple")); //mid insert
        arr.add(new Richpeople(52, 123456, "Apple")); //tail insert
        return arr;
    }

}