package com.example.demo.models.sorts;
import com.example.demo.controller.*;
import minilabz.*;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.ArrayList;

public class Bubble {

    public static int[] bubble(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    public static String[] bubble(String[] arr) {
        ArrayList<String> strarr = new ArrayList<>();
        ArrayList<Integer> intarr = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {  //sorts ints and chars separately
            try {
                intarr.add(Integer.parseInt(arr[i]));
            } catch (NumberFormatException e) {
                strarr.add(arr[i]);
            }
        }

        for (int j = 0; j < strarr.size() - 1; j++)
        {
            for (int i = j + 1; i < strarr.size(); i++)
            {
                if (strarr.get(j).compareTo(strarr.get(i)) > 0)
                {
                    String temp = strarr.get(j);
                    strarr.set(j, strarr.get(i));
                    strarr.set(i, temp);
                }
            }
        }

        for (int j = 0; j < intarr.size() - 1; j++)
        {
            for (int i = j + 1; i < intarr.size(); i++)
            {
                if (intarr.get(j).compareTo(intarr.get(i)) > 0)
                {
                    int temp = intarr.get(j);
                    intarr.set(j, intarr.get(i));
                    intarr.set(i, temp);
                }
            }
        }

        for (int i = 0; i < intarr.size(); i++) { //merges int and str arrays together after sort with ints in front
            arr[i] = Integer.toString(intarr.get(i));
        }

        for (int i = intarr.size(); i < arr.length; i++) {
            arr[i] = strarr.get(i - intarr.size());
        }

        return arr;
    }

    public static Employee[] bubble(Employee[] arr) {
        int n = arr.length;

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr[j].toString().compareTo(arr[i].toString()) > 0)
                {
                    Employee temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }

    public static Athlete[] bubble(Athlete[] arr) {
        int n = arr.length;

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr[j].toString().compareTo(arr[i].toString()) > 0)
                {
                    Athlete temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }

    public static Richpeople[] bubble(Richpeople[] arr) {
        int n = arr.length;

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr[j].toString().compareTo(arr[i].toString()) > 0)
                {
                    Richpeople temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }

    public static Water[] bubble(Water[] arr) {
        int n = arr.length;

        for (int a = 0; a < n - 1; a++)
        {
            for (int i = a + 1; i < n; i++)
            {
                if (arr[a].toString().compareTo(arr[i].toString()) > 0)
                {
                    Water temp = arr[a];
                    arr[a] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }
}
