package minilabs;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class BinarySearchController {

    public BinarysearchController(){
        int arr[] = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 10;
        Fibonacci(arr, 0, n - 1, x);
    }

    public void Fibonacci(int arr[], int l, int r, int x) {
        BinarySearchModel model = new BinarySearchModel(arr, l, r, x);
        model.print();
    }
}
