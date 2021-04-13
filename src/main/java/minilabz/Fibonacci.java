package minilabz;

import java.util.ArrayList;

public class Fibonacci{
    public static ArrayList<Integer> listRecurse = new ArrayList<>();
    public static ArrayList<Integer> listFor = new ArrayList<>();
    public static ArrayList<Integer> listWhile = new ArrayList<>();

    public static void Recurse(long size, Integer num1, Integer num2) {
        assert size >= 2 : "'Size' must be at least 2";
        listRecurse.add(num1);
        if (size == 2) {
            listRecurse.add(num2);
        } else {
            Recurse(size - 1, num2, num1+num2);
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
        listWhile.add(num1);
        listWhile.add(num2);
        int i = 2;
        while (i < size) {
            Integer first = listWhile.get(i-2);
            Integer second = listWhile.get(i-1);
            listWhile.add(first+second);
            i++;
        }
    }

    public static String RecResult() {
        String result = "" + listRecurse.get(0);
        for (int i = 1; i < listRecurse.size(); i++) {
            result += ", " + listRecurse.get(i);
        }
        return result;
    }

    public static String ForResult() {
        String result = "" + listFor.get(0);
        for (int i = 1; i < listFor.size(); i++) {
            result += ", " + listFor.get(i);
        }
        return result;
    }

    public static String WhileResult() {
        String result = "" + listWhile.get(0);
        for (int i = 1; i < listWhile.size(); i++) {
            result += ", " + listWhile.get(i);
        }
        return result;
    }
}