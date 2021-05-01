package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;

import java.util.ArrayList;

public class seanSort {
    public static ArrayList<Athlete> bsort(ArrayList<Athlete> arr) {
        int n = arr.size();

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr.get(j).toString().compareTo(arr.get(i).toString()) > 0)
                {
                    Athlete temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
        return arr;
    }

    public static java.util.LinkedList<Athlete> bsort(java.util.LinkedList<Athlete> arr) {
        int n = arr.size();

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr.get(j).toString().compareTo(arr.get(i).toString()) > 0)
                {
                    Athlete temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
        return arr;
    }
}
