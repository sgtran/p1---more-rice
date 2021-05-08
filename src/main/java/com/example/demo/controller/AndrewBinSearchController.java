package com.example.demo.controller;

import lombok.Getter;
import minilabz.BinarySearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Controller
public class AndrewBinSearchController {

    @GetMapping("/binSearchAP")
    public String binSearchAP(@RequestParam(name="name", required=false,  defaultValue="Alex") String name, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("binSearchAP");
        String input = name;

        //Benchmarks
        minilabz.BinarySearch.init();
        String array = minilabz.BinarySearch.printArray();
        minilabz.BinarySearch.sort();
        String sorted = minilabz.BinarySearch.printArray();
        long start = System.nanoTime();
        int index = minilabz.BinarySearch.search(input);
        long end = System.nanoTime() - start;

        model.addAttribute("array", array);
        model.addAttribute("sorted", sorted);
        model.addAttribute("name", input);
        model.addAttribute("result", index);
        model.addAttribute("time", end);

        return "binSearchAP";

    }

}
