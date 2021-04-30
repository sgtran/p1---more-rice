package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;

import java.util.ArrayList;

public class Delete {
    public static ArrayList<Employee> delete (ArrayList<Employee> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }

    public static java.util.LinkedList<Employee> delete(java.util.LinkedList<Employee> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }
}
