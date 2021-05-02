package com.example.demo.models.sorts;
import com.example.demo.controller.*;
import minilabz.Athlete;
import minilabz.Employee;
import minilabz.Richpeople;
import minilabz.Water;

public class Insertion {
    public static int[] insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > current) { //moves backwards along the array and shifts all greater values up by 1 slot
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current; //then assigns the current value to the left end of this shift
        }

        return arr;
    }

    public static String[] insertion(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(current) > 0) { //moves backwards along the array and shifts all greater values up by 1 slot
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current; //then assigns the current value to the left end of this shift
        }

        return arr;
    }

    public static Employee[] insertion(Employee[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Employee current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].toString().compareTo(current.toString()) > 0) { //moves backwards along the array and shifts all greater values up by 1 slot
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current; //then assigns the current value to the left end of this shift
        }

        return arr;
    }

    public static Richpeople[] insertion(Richpeople[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Richpeople current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].toString().compareTo(current.toString()) > 0) { //moves backwards along the array and shifts all greater values up by 1 slot
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current; //then assigns the current value to the left end of this shift
        }

        return arr;
    }

    public static Athlete[] insertion(Athlete[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Athlete current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].toString().compareTo(current.toString()) > 0) { //moves backwards along the array and shifts all greater values up by 1 slot
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current; //then assigns the current value to the left end of this shift
        }

        return arr;
    }

    public static Water[] insertion(Water[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Water current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].toString().compareTo(current.toString()) > 0) {
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = current;
        }

        return arr;
    }
}
