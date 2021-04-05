
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Scanner;

@Controller
public class BinarySearchController {

    public BinarySearchController(){
        int arr[] = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 10;
        System.out.println("Submitted array: " + Arrays.toString(arr));
        System.out.println("Element to be found: " + Integer.toString(x));
        binarySearch(arr, 0, n - 1, x);
    }

    public void binarySearch(int arr[], int l, int r, int x) {
        BinarySearchModel model = new BinarySearchModel(arr, l, r, x);
    }
}
