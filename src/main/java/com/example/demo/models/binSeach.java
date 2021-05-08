package com.example.demo.models;
import com.example.demo.models.sorts.Bubble;
import java.lang.System;

public class binSeach {
    public String inp;
    public String sf;
    public int index;
    public long bint, lint;

    public binSeach() {
        this.index = 0;
        this.inp = "";
        this.sf = "";
    }

    public int getIndex() {
        return index;
    }

    public String getinp() {
        return inp;
    }

    public String getSf() {
        return sf;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public void setSf(String sf) { //main running part of code is here as there doesn't seem to be any other way to run a function when submit is clicked
        this.sf = sf;

        if (inp.length() == 0 && sf.length() == 0) { //default input
            inp = "111111111111111111111111444444441251251223532444444444999999";
            sf = "9";
        }

        String[] input = new String[inp.length()];

        for (int i = 0; i < inp.length(); i++) {
            input[i] = Character.toString(inp.charAt(i));
        }

        Bubble.bubble(input);

        try {
            this.bint = System.nanoTime();
            this.index = search(input, sf.charAt(0), 0, inp.length());
            this.bint = System.nanoTime() - this.bint;
        } catch (StackOverflowError e) {
            this.index = -1;
        }

        this.lint = System.nanoTime();
        this.index = linsearch(input, sf.charAt(0));
        this.lint = System.nanoTime() - this.lint;
    }

    public int search(String[] input, char c, int l, int h) {
        int index2 = (int)(l + (h - l) / 2);

        if (input[index2].equals(Character.toString(c))) {
            return index2;
        } else {
            if (input[index2].compareTo(Character.toString(c)) > 0) {
                return search(input, c, 0, index2 - 1);
            } else {
                return search(input, c, index2 + 1, h);
            }
        }
    }

    public int linsearch(String[] input, char c) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(Character.toString(c))) {
                return i;
            }
        }

        return -1;
    }
}
