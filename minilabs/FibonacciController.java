package minilabs;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FibonacciController{

    public FibonacciController(){
        Fibonacci(20);
    }

    public void Fibonacci(size) {
        FibonacciModel model = new FibonacciModel(size);
        model.print();

        Scanner obj = new Scanner(System.in);
        int nth = obj.nextInt();
        Fibonacci(nth);
    }

}
