package com.example.demo.models;

public class Factorial {
    private long input;
    private static long output;

    public Factorial(long input) {
        this.input = input;
        this.output = setOutput(input);
    }

    public long setOutput(long input) {
        if (input > 1) {return (input*setOutput((input-1)));}

        else {return 1;}
    }

    public static long getOutput() {
        return output;
    }
}
