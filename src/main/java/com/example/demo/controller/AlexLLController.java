package com.example.demo.controller;
import com.example.demo.models.*;
import com.example.demo.models.linkedlists.Delete;
import com.example.demo.models.linkedlists.Insert;
import com.example.demo.models.linkedlists.Sort;
import lombok.Getter;
import minilabz.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.System;

@Getter
@Controller
public class AlexLLController {
    public ArrayList<Employee> al = new ArrayList<>();
    public LinkedList<Employee> ll = new LinkedList<>();

    public String alToString(ArrayList<Employee> arr) {
        String alarr = "";

        for (int i = 0; i < arr.size(); i++) {
            alarr += arr.get(i).toString();
        }

        return alarr;
    }

    public String llToString(LinkedList<Employee> arr) {
        String llarr = "";

        for (int i = 0; i < arr.size(); i++) {
            llarr += arr.get(i).toString();
        }

        return llarr;
    }

    @GetMapping("/alexLL")
    public String test(Long a, Long l, String altimei, String lltimei, String altimed, String lltimed, String altimes, String lltimes, String aldispi, String lldispi, String aldispd, String lldispd, String aldisps, String lldisps, String analysis, String albefore, String llbefore, org.springframework.ui.Model model) {
        al.clear();
        ll.clear();

        al.add(new Employee(2, 80000, "Scientist"));
        al.add(new Employee(7, 75000, "Teacher"));
        al.add(new Employee(19, 110000, "Computer Scientist"));
        al.add(new Employee(0, 50000, "Restaurant Worker"));

        ll.add(new Employee(2, 80000, "Scientist"));
        ll.add(new Employee(7, 75000, "Teacher"));
        ll.add(new Employee(19, 110000, "Computer Scientist"));
        ll.add(new Employee(0, 50000, "Restaurant Worker"));

        albefore = alToString(al);
        llbefore = llToString(ll);

        a = java.lang.System.nanoTime();
        aldispi = alToString(Insert.insert(al)); //insert
        altimei = "ArrayList insert time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispi = llToString(Insert.insert(ll)); //insert
        lltimei = "LinkedList insert time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldispd = alToString(Delete.delete(al)); //delete
        altimed = "ArrayList delete time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispd = llToString(Delete.delete(ll)); //delete
        lltimed = "LinkedList delete time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldisps = alToString(Sort.bsort(al)); //sort
        altimes = "ArrayList sort time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldisps = llToString(Sort.bsort(ll)); //sort
        lltimes = "LinkedList sort time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        analysis = "ArrayList add: O(n), ArrayList remove: O(n), ArrayList bubble sort: O(n^2); LinkedList add: O(1), LinkedList remove: O(1), LinkedList bubble sort: O(n^2)";

        model.addAttribute("albefore", albefore);
        model.addAttribute("llbefore", llbefore);
        model.addAttribute("aldispi", aldispi);
        model.addAttribute("lldispi", lldispi);
        model.addAttribute("altimei", altimei);
        model.addAttribute("lltimei", lltimei);
        model.addAttribute("aldispd", aldispd);
        model.addAttribute("lldispd", lldispd);
        model.addAttribute("altimed", altimed);
        model.addAttribute("lltimed", lltimed);
        model.addAttribute("aldisps", aldisps);
        model.addAttribute("lldisps", lldisps);
        model.addAttribute("altimes", altimes);
        model.addAttribute("lltimes", lltimes);
        model.addAttribute("analysis", analysis);

        return "alexLL";
    }
}
