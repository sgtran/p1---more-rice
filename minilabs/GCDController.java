
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GCDController{

    public GCDController(){
        getInput();
    }

    public void getInput() {
        System.out.println("Please give positive integer: "); //for console output
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();


        System.out.println("Please give positive integer: "); //for console output
        Scanner scannerTwo = new Scanner(System.in);
        int m = scanner.nextInt();

        if (n <= 0 || m <= 0){
            System.out.println("Error in one or more variables due to it being negative. Expect GCD to be incorrect.");
        }
        System.out.println("The greatest common denominator is " + GCDModel.gcd(n,m));
    }
}
