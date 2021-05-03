package com.example.demo.models.linkedlists;

import minilabz.Athlete;
import minilabz.Water;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KevinSort {
    public static ArrayList<Water> selectSort(ArrayList<Water> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j).toString().compareTo(arr.get(minPos).toString()) < 0) {
                    minPos = j;
                }
            }
            Water temp = arr.get(minPos);
            arr.set(minPos, arr.get(i));
            arr.set(i, temp);
        }

        return arr;
    }

    public static java.util.LinkedList<Water> selectSort(java.util.LinkedList<Water> arr){
        for (int i = 0; i < arr.size() - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j).toString().compareTo(arr.get(minPos).toString()) < 0) {
                    minPos = j;
                }
            }
            Water temp = arr.get(minPos);
            arr.set(minPos, arr.get(i));
            arr.set(i, temp);
        }

        return arr;
    }
}
