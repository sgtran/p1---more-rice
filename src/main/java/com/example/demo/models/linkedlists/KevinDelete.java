package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;

import java.util.ArrayList;

public class KevinDelete {
    public static ArrayList<Water> delete (ArrayList<Water> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }

    public static java.util.LinkedList<Water> delete(java.util.LinkedList<Water> arr) {
        arr.remove(0); //head remove
        arr.remove(3); //mid remove
        arr.remove(arr.size() - 1); //tail remove
        return arr;
    }


}
