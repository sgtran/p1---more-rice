package minilabs;

import java.util.ArrayList;

public class FibonacciModel {

    ArrayList<int> list;
    int size;

    public FibonacciModel(int s) {
        size = s;
        list.add(0);
        list.add(1);
        Factorial(size, 0, 1);
    }

    //size must be at least 2
    public void Factorial(int size, int a, int b) {
        if (size == 2) {return;}
        list.add(a + b);
        Factorial(size - 1, b, a + b);
    }

    public void print() {
        System.out.println("Sequence Size: " + size);
        System.out.print("Sequence: " + list[0]);
        for(int i = 1; i < size; i++) {
            System.out.print(", " + list[i]);
        }
    }

}