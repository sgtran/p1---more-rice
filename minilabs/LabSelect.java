package minilabs;

import java.util.List;
import java.util.Scanner;

public class LabSelect {
    private static int selection;
    private static String[] options = {"1. Factorial"};

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        for (String i: options){
            System.out.println(i);
        }
        System.out.println("Select minilab: ");
        selection = scan.nextInt();
        System.out.println(Integer.toString(selection));

        switch(selection){
            case 1:
                FactorialController fact = new FactorialController();

        }
    }

}