package com.example.demo.controller;
import com.example.demo.models.*;
import com.example.demo.models.linkedlists.KevinDelete;
import com.example.demo.models.linkedlists.KevinInsert;
import com.example.demo.models.linkedlists.KevinSort;
import lombok.Getter;
import minilabz.Water;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.System;

@Getter
@Controller
public class KevinLLController {
    public ArrayList<Water> al = new ArrayList<>();
    public LinkedList<Water> ll = new LinkedList<>();

    public String alToString(ArrayList<Water> arr) {
        String alarr = "";

        for (int i = 0; i < arr.size(); i++) {
            alarr += arr.get(i).toString();
        }

        return alarr;
    }

    public String llToString(LinkedList<Water> arr) {
        String llarr = "";

        for (int i = 0; i < arr.size(); i++) {
            llarr += arr.get(i).toString();
        }

        return llarr;
    }

    @GetMapping("/kevinLL")
    public String test(Long a, Long l, String altimei, String lltimei, String altimed, String lltimed, String altimes, String lltimes, String aldispi, String lldispi, String aldispd, String lldispd, String aldisps, String lldisps, String analysis, String albefore, String llbefore, org.springframework.ui.Model model) {
        al.clear();
        ll.clear();

        al.add(new Water(2, 80000, "SmartWater"));
        al.add(new Water(7, 75000, "Fiji"));
        al.add(new Water(19, 110000, "Arrowhead"));
        al.add(new Water(0, 50000, "PureWater"));

        ll.add(new Water(2, 80000, "SmartWater"));
        ll.add(new Water(7, 75000, "Fiji"));
        ll.add(new Water(19, 110000, "Arrowhead"));
        ll.add(new Water(0, 50000, "PureWater"));

        albefore = alToString(al);
        llbefore = llToString(ll);

        a = java.lang.System.nanoTime();
        aldispi = alToString(KevinInsert.insert(al)); //insert
        altimei = "ArrayList insert time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispi = llToString(KevinInsert.insert(ll)); //insert
        lltimei = "LinkedList insert time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldispd = alToString(KevinDelete.delete(al)); //delete
        altimed = "ArrayList delete time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispd = llToString(KevinDelete.delete(ll)); //delete
        lltimed = "LinkedList delete time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldisps = alToString(KevinSort.selectSort(al)); //sort
        altimes = "ArrayList sort time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldisps = llToString(KevinSort.selectSort(ll)); //sort
        lltimes = "LinkedList sort time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        analysis = "ArrayList add: O(n), ArrayList remove: O(n), ArrayList selection sort: O(n^2); LinkedList traversal: O(n); LinkedList insert: O(1), LinkedList remove: O(1), LinkedList selection sort: O(n^2)";

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

        return "kevinLL";
    }
}
