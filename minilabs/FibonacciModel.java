
import java.util.ArrayList;

public class FibonacciModel {

    ArrayList<Integer> list = new ArrayList<Integer>();
    int size;

    public FibonacciModel(int s) {
        size = s;
        list.add(0);
        list.add(1);
        Fibonacci(size, 0, 1);
    }

    //size must be at least 2
    public void Fibonacci(int size, int a, int b) {
        if (size == 2) {return;}
        list.add(a + b);
        Fibonacci(size - 1, b, a + b);
    }

    public void print() {
        System.out.println("Sequence Size: " + size);
        System.out.print("Sequence: " + list.get(0));
        for(int i = 1; i < size; i++) {
            System.out.print(", " + list.get(i));
        }
    }

}