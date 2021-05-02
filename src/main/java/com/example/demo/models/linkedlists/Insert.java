package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;

import java.util.ArrayList;
import java.util.LinkedList;

public class Insert {
    public static ArrayList<Employee> insert (ArrayList<Employee> arr) {
        arr.add(0, new Employee(10, 100000, "Computer Scientist")); //head insert
        arr.add(2, new Employee(10, 100000, "Computer Scientist")); //mid insert
        arr.add(new Employee(10, 100000, "Computer Scientist")); //tail insert
        return arr;
    }

    public static java.util.LinkedList<Employee> insert(java.util.LinkedList<Employee> arr) {
        arr.add(0, new Employee(10, 100000, "Computer Scientist")); //head insert
        arr.add(2, new Employee(10, 100000, "Computer Scientist")); //mid insert
        arr.add(new Employee(10, 100000, "Computer Scientist")); //tail insert
        return arr;
    }

}
