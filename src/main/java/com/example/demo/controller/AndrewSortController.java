package com.example.demo.controller;

import consoleUI.ConsoleMethods;
import minilabz.*;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static minilabz.Fibonacci.listRecurse;

@Getter
@Controller
public class AndrewSortController {

    @GetMapping("/andrewRealSort")
    public String andrewRealSort(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("andrewRealSort");

        // Benchmarks
        minilabz.AndrewSort.reset();
        long StringBubbleStart = System.nanoTime();
        String StringBubbleResult = minilabz.AndrewSort.StringBubbleSort(minilabz.AndrewSort.Strings);
        long StringBubbleTime = System.nanoTime() - StringBubbleStart;
        minilabz.AndrewSort.reset();
        long intBubbleStart = System.nanoTime();
        String intBubbleResult = minilabz.AndrewSort.intBubbleSort(minilabz.AndrewSort.Integers);
        long intBubbleTime = System.nanoTime() - intBubbleStart;
        minilabz.AndrewSort.reset();
        long PetBubbleStart = System.nanoTime();
        String PetBubbleResult = minilabz.AndrewSort.PetBubbleSort(minilabz.AndrewSort.Pets);
        long PetBubbleTime = System.nanoTime() - PetBubbleStart;
        minilabz.AndrewSort.reset();
        long StringSelectionStart = System.nanoTime();
        String StringSelectionResult = minilabz.AndrewSort.StringSelectionSort(minilabz.AndrewSort.Strings);
        long StringSelectionTime = System.nanoTime() - StringSelectionStart;
        minilabz.AndrewSort.reset();
        long intSelectionStart = System.nanoTime();
        String intSelectionResult = minilabz.AndrewSort.intSelectionSort(minilabz.AndrewSort.Integers);
        long intSelectionTime = System.nanoTime() - intSelectionStart;
        minilabz.AndrewSort.reset();
        long PetSelectionStart = System.nanoTime();
        String PetSelectionResult = minilabz.AndrewSort.PetSelectionSort(minilabz.AndrewSort.Pets);
        long PetSelectionTime = System.nanoTime() - PetSelectionStart;
        minilabz.AndrewSort.reset();
        long StringInsertionStart = System.nanoTime();
        String StringInsertionResult = minilabz.AndrewSort.StringInsertionSort(minilabz.AndrewSort.Strings);
        long StringInsertionTime = System.nanoTime() - StringInsertionStart;
        minilabz.AndrewSort.reset();
        long intInsertionStart = System.nanoTime();
        String intInsertionResult = minilabz.AndrewSort.intInsertionSort(minilabz.AndrewSort.Integers);
        long intInsertionTime = System.nanoTime() - intInsertionStart;
        minilabz.AndrewSort.reset();
        long PetInsertionStart = System.nanoTime();
        String PetInsertionResult = minilabz.AndrewSort.PetInsertionSort(minilabz.AndrewSort.Pets);
        long PetInsertionTime = System.nanoTime() - PetInsertionStart;

        model.addAttribute("Result1", StringBubbleResult);
        model.addAttribute("Result2", intBubbleResult);
        model.addAttribute("Result3", PetBubbleResult);
        model.addAttribute("Time1", StringBubbleTime / 1000000.0);
        model.addAttribute("Time2", intBubbleTime / 1000000.0);
        model.addAttribute("Time3", PetBubbleTime / 1000000.0);
        model.addAttribute("Result4", StringSelectionResult);
        model.addAttribute("Result5", intSelectionResult);
        model.addAttribute("Result6", PetSelectionResult);
        model.addAttribute("Time4", StringSelectionTime / 1000000.0);
        model.addAttribute("Time5", intSelectionTime / 1000000.0);
        model.addAttribute("Time6", PetSelectionTime / 1000000.0);
        model.addAttribute("Result7", StringInsertionResult);
        model.addAttribute("Result8", intInsertionResult);
        model.addAttribute("Result9", PetInsertionResult);
        model.addAttribute("Time7", StringInsertionTime / 1000000.0);
        model.addAttribute("Time8", intInsertionTime / 1000000.0);
        model.addAttribute("Time9", PetInsertionTime / 1000000.0);

        return "andrewRealSort";

    }

}
