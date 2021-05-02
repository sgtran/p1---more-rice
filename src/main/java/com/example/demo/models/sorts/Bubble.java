package com.example.demo.models.sorts;
import com.example.demo.controller.*;
import minilabz.*;

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
        int n = arr.length;

        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (arr[j].compareTo(arr[i]) > 0)
                {
                    String temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
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
