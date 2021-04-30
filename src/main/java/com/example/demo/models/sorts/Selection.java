package com.example.demo.models.sorts;
import com.example.demo.controller.*;
import minilabz.Athlete;
import minilabz.Employee;

public class Selection {
    public static int[] selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //increases lower bound of array by 1 per loop
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) { //checks the array from current lower bound to max for the minimum value
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }

            int temp = arr[minPos]; //swaps the minimum value and the lower bound value
            arr[minPos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public static String[] selection(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //increases lower bound of array by 1 per loop
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) { //checks the array from current lower bound to max for the minimum value
                if (arr[j].compareTo(arr[minPos]) < 0) {
                    minPos = j;
                }
            }

            String temp = arr[minPos]; //swaps the minimum value and the lower bound value
            arr[minPos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public static Employee[] selection(Employee[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //increases lower bound of array by 1 per loop
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) { //checks the array from current lower bound to max for the minimum value
                if (arr[j].toString().compareTo(arr[minPos].toString()) < 0) {
                    minPos = j;
                }
            }

            Employee temp = arr[minPos]; //swaps the minimum value and the lower bound value
            arr[minPos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public static Athlete[] selection(Athlete[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //increases lower bound of array by 1 per loop
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) { //checks the array from current lower bound to max for the minimum value
                if (arr[j].toString().compareTo(arr[minPos].toString()) < 0) {
                    minPos = j;
                }
            }

            Athlete temp = arr[minPos]; //swaps the minimum value and the lower bound value
            arr[minPos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }
}
