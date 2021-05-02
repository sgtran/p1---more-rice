package com.example.demo.models.linkedlists;
import com.example.demo.controller.*;
import minilabz.*;

import java.util.ArrayList;

public class atharvaSort {
    public static ArrayList<Richpeople> bsort(ArrayList<Richpeople> arr) {
        int n = arr.size();

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr.get(j).toString().compareTo(arr.get(i).toString()) > 0)
                {
                    Richpeople temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
        return arr;
    }

    public static java.util.LinkedList<Richpeople> bsort(java.util.LinkedList<Richpeople> arr) {
        int n = arr.size();

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr.get(j).toString().compareTo(arr.get(i).toString()) > 0)
                {
                    Richpeople temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
        return arr;
    }
}
