package com.nighthawk.csa.algorithm.fibonacciModel;

import java.util.ArrayList;

public class Fibonacci{
    public ArrayList<Integer> listRecurse;
    public ArrayList<Integer> listFor;
    public ArrayList<Integer> listWhile;
    private long size;

    public static void Recurse(long size, Integer num1, Integer num2) {
        assert size >= 2 : "'Size' must be at least 2";
        listRecurse.add(num1);
        if (size == 2) {
            listRecurse.add(num2);
        } else {
            Recurse(size - 1, num1, num2);
        }

    }

    public static void For(long size, Integer num1, Integer num2) {
        assert size >= 2 : "'Size' must be at least 2";
        listFor.add(num1);
        listFor.add(num2);
        for (int i = 2; i < size; i++) {
            Integer first = listFor.get(i-2);
            Integer second = listFor.get(i-1);
            listFor.add(first+second);
        }
    }

    public static void While(long size, Integer num1, Integer num2) {
        assert size >= 2 : "'Size' must be at least 2";
        listFor.add(num1);
        listFor.add(num2);
        int i = 2;
        while (i < size) {
            Integer first = listFor.get(i-2);
            Integer second = listFor.get(i-1);
            listFor.add(first+second);
            i++;
        }
    }

    public static String RecResult() {
        String result = "" + listRecurse.get(0);
        for (int i = 1; i < listRecures.size(); i++) {
            result += ", " + listRecurse.get(i);
        }
        return result;
    }
}