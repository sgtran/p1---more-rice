

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FactorialController{

    public FactorialController(){
        getInput();
    }

    public void getInput() {
        System.out.println("Enter a positive integer: "); //for console output
        Scanner scanner = new Scanner(System.in);
        int nth = scanner.nextInt();

        if (FactModel.fact(nth) != -1) {
            System.out.println(FactModel.fact(nth)); //console output
        } else {
            System.out.println("Please enter a positive integer");
        }
    }
}
