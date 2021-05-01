package com.example.demo.controller;
import com.example.demo.models.*;
import com.example.demo.models.linkedlists.seanDelete;
import com.example.demo.models.linkedlists.Insert;
import com.example.demo.models.linkedlists.seanInsert;

import com.example.demo.models.linkedlists.seanSort;
import lombok.Getter;
import minilabz.Athlete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.System;

@Getter
@Controller
public class SeanLLController {
    public ArrayList<Athlete> al = new ArrayList<>();
    public LinkedList<Athlete> ll = new LinkedList<>();

    public String alToString(ArrayList<Athlete> arr) {
        String alarr = "";

        for (int i = 0; i < arr.size(); i++) {
            alarr += arr.get(i).toString();
        }

        return alarr;
    }

    public String llToString(LinkedList<Athlete> arr) {
        String llarr = "";

        for (int i = 0; i < arr.size(); i++) {
            llarr += arr.get(i).toString();
        }

        return llarr;
    }

    @GetMapping("/seanLL")
    public String test(Long a, Long l, String altimei, String lltimei, String altimed, String lltimed, String altimes, String lltimes, String aldispi, String lldispi, String aldispd, String lldispd, String aldisps, String lldisps, String analysis, String albefore, String llbefore, org.springframework.ui.Model model) {
        al.clear();
        ll.clear();

        al.add(new Athlete(2, 70000, "Basketball"));
        al.add(new Athlete(7, 65000, "Baseball"));
        al.add(new Athlete(19, 10000, "Golf"));
        al.add(new Athlete(0, 40000, "Football"));

        ll.add(new Athlete(2, 70000, "Basketball"));
        ll.add(new Athlete(7, 65000, "Baseball"));
        ll.add(new Athlete(19, 10000, "Golf"));
        ll.add(new Athlete(0, 40000, "Football"));

        albefore = alToString(al);
        llbefore = llToString(ll);

        a = java.lang.System.nanoTime();
        aldispi = alToString(seanInsert.insert(al)); //insert
        altimei = "ArrayList insert time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispi = llToString(seanInsert.insert(ll)); //insert
        lltimei = "LinkedList insert time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldispd = alToString(seanDelete.delete(al)); //delete
        altimed = "ArrayList delete time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldispd = llToString(seanDelete.delete(ll)); //delete
        lltimed = "LinkedList delete time: " + Long.toString((java.lang.System.nanoTime() - l) / 1000) + " microseconds"; //calculate time required in microseconds

        a = java.lang.System.nanoTime();
        aldisps = alToString(seanSort.bsort(al)); //sort
        altimes = "ArrayList sort time: " + Long.toString((java.lang.System.nanoTime() - a) / 1000) + " microseconds"; //calculate time required in microseconds

        l = java.lang.System.nanoTime();
        lldisps = llToString(seanSort.bsort(ll)); //sort
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

        return "seanLL";
    }
}
