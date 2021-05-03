package com.example.demo.controller;

import minilabz.*;
import java.lang.System.*;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Controller
public class KevinSortController {
    public long b, i, s; 
    public int[] intArr = new int[]{0, 2, 6, 78, 20, 43};
    public String[] stringArr = new String[]{"a", "c", "e", "f", "h", "j", "z", "u"};
    public Water[] waterArr = new Water[]{new Water(10, 90000, "Aquafina"), new Water(6, 1, "Dasani"), new Water(5, 50, "Arrowhead"), new Water(1, 200000, "Kirkland"), new Water(3, 100, "Fiji")};

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

    public String arrToString (Water[] arr) {
        String fin = "";
        for (int i = 0; i < arr.length; i++) {
            fin += arr[i]; //automatically calls tostring
        }

        return fin;
    }

    @GetMapping("/kevinSorts2")
    public String test(String analysis, String bubblei, String bubbles, String bubblee, String inserti, String inserts, String inserte, String selecti, String selects, String selecte, String btime, String itime, String stime, Model model) {
        b = java.lang.System.nanoTime();

        bubblei = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(intArr));
        bubbles = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(stringArr));
        bubblee = "Bubble: " + arrToString(com.example.demo.models.sorts.Bubble.bubble(waterArr));
        btime = "Time: " + Long.toString((java.lang.System.nanoTime() - b) / 1000) + " microseconds";

        i = java.lang.System.nanoTime();
        inserti = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(intArr));
        inserts = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(stringArr));
        inserte = "Insertion: " + arrToString(com.example.demo.models.sorts.Insertion.insertion(waterArr));
        itime = "Time: " + Long.toString((java.lang.System.nanoTime() - i) / 1000) + " microseconds";

        s = java.lang.System.nanoTime();
        selecti = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(intArr));
        selects = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(stringArr));
        selecte = "Selection: " + arrToString(com.example.demo.models.sorts.Selection.selection(waterArr));
        stime = "Time: " + Long.toString((java.lang.System.nanoTime() - s) / 1000) + " microseconds";



        model.addAttribute("bubblei", bubblei);
        model.addAttribute("bubbles", bubbles);
        model.addAttribute("bubblee", bubblee);
        model.addAttribute("btime", btime);

        model.addAttribute("inserti", inserti);
        model.addAttribute("inserts", inserts);
        model.addAttribute("inserte", inserte);
        model.addAttribute("itime", itime);

        model.addAttribute("selecti", selecti);
        model.addAttribute("selects", selects);
        model.addAttribute("selecte", selecte);
        model.addAttribute("stime", stime);

        analysis = "Bubble Sort: O(n^2), Insertion sort: O(n), Selection Sort: O(n^2); All sorts have same time complexity so there is no best sort between these three in terms of worst case scenario. However, in best case scenario Bubble and Insertion sort would be the best with O(n) each for best case scenario.";
        model.addAttribute("analysis", analysis);

        return "kevinSorts2";
    }

}
