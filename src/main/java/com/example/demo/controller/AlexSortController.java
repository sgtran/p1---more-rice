package com.example.demo.controller;

import minilabz.*;
import java.lang.System.*;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Controller
public class AlexSortController {
    public long b, i, s; //for tracking nanoseconds
    public int[] intArr = new int[]{5, 2, 6, 3, 7};
    public String[] stringArr = new String[]{"s", "f", "t", "c", "i"};
    public Employee[] employeeArr = new Employee[]{new Employee(10, 100000, "Computer Scientist"), new Employee(2, 80000, "Scientist"), new Employee(7, 75000, "Teacher"), new Employee(19, 110000, "Computer Scientist"), new Employee(0, 50000, "Restaurant Worker")};

    public String arrToString (int[] arr) {
        String fin = "";
        for (int i = 0; i < arr.length; i++) {
            fin += Integer.toString(arr[i]) + ", ";
        }

        return fin;
    }

    public String arrToString (String[] arr) {
        String fin = "";
        for (int i = 0; i < arr.length; i++) {
            fin += arr[i] + ", ";
        }

        return fin;
    }

    public String arrToString (Employee[] arr) {
        String fin = "";
        for (int i = 0; i < arr.length; i++) {
            fin += arr[i]; //automatically calls tostring
        }

        return fin;
    }

    @GetMapping("/alexSorts2")
    public String test(String bubbleint, String bubblestr, String bubbleemp, String insertint, String insertstr, String insertemp, String selectint, String selectstr, String selectemp, String btime, String itime, String stime, Model model) {
        b = java.lang.System.nanoTime();
        bubbleint = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(intArr)); //this value is displayed on the page
        bubblestr = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(stringArr));
        bubbleemp = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(employeeArr));
        btime = "Time: " + Long.toString((java.lang.System.nanoTime() - b) / 1000) + " microseconds"; //time it took for the sorts

        i = java.lang.System.nanoTime();
        insertint = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(intArr)); //this value is displayed on the page
        insertstr = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(stringArr));
        insertemp = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(employeeArr));
        itime = "Time: " + Long.toString((java.lang.System.nanoTime() - i) / 1000) + " microseconds";

        s = java.lang.System.nanoTime();
        selectint = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(intArr)); //this value is displayed on the page
        selectstr = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(stringArr));
        selectemp = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(employeeArr));
        stime = "Time: " + Long.toString((java.lang.System.nanoTime() - s) / 1000) + " microseconds";

        model.addAttribute("bubbleint", bubbleint); //returns this value to the bubbleint key in the html, causing thymeleaf to print the value associated with the bubbleint string onto the webpage
        model.addAttribute("bubblestr", bubblestr);
        model.addAttribute("bubbleemp", bubbleemp);
        model.addAttribute("btime", btime);
        
        model.addAttribute("insertint", insertint);
        model.addAttribute("insertstr", insertstr);
        model.addAttribute("insertemp", insertemp);
        model.addAttribute("itime", itime);
        
        model.addAttribute("selectint", selectint);
        model.addAttribute("selectstr", selectstr);
        model.addAttribute("selectemp", selectemp);
        model.addAttribute("stime", stime);

        return "alexSorts2";
    }

}
