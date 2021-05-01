package com.example.demo.controller;

import minilabz.*;
import java.lang.System.*;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Controller
public class AtharvaSortController {
    public long b, i, s; //for tracking nanoseconds
    public int[] intArr = new int[]{4, 1, 19, 14, 12};
    public String[] stringArr = new String[]{"e", "s", "i", "v", "m"};
    public Richpeople[] RichpeopleArr = new Richpeople[]{new Richpeople(52, 100000, "Apple"), new Richpeople(48, 80000, "Amazon"), new Richpeople(59, 75000, "Google"), new Richpeople(33, 110000, "Netflix"), new Richpeople(42, 50000, "Facebook")};

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

    public String arrToString (Richpeople[] arr) {
        String fin = "";
        for (int i = 0; i < arr.length; i++) {
            fin += arr[i]; //automatically calls tostring
        }

        return fin;
    }

    @GetMapping("/atharvaSort")
    public String test(String bubbleint, String bubblestr, String bubbleemp, String insertint, String insertstr, String insertemp, String selectint, String selectstr, String selectemp, String btime, String itime, String stime, String results, Model model) {
        b = java.lang.System.nanoTime();
        bubbleint = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(intArr)); //this value is displayed on the page
        bubblestr = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(stringArr));
        bubbleemp = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(RichpeopleArr));
        btime = "Time: " + Long.toString((java.lang.System.nanoTime() - b) / 1000) + " microseconds"; //time it took for the sorts

        i = java.lang.System.nanoTime();
        insertint = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(intArr)); //this value is displayed on the page
        insertstr = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(stringArr));
        insertemp = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(RichpeopleArr));
        itime = "Time: " + Long.toString((java.lang.System.nanoTime() - i) / 1000) + " microseconds";

        s = java.lang.System.nanoTime();
        selectint = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(intArr)); //this value is displayed on the page
        selectstr = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(stringArr));
        selectemp = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(RichpeopleArr));
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

        results = "Bubble sort: O(n^2) average and best case; Insertion sort: O(n^2) average to O(n) best case; Selection sort: O(n^2) average and best case";
        model.addAttribute("results", results);

        return "atharvaSort";
    }

}