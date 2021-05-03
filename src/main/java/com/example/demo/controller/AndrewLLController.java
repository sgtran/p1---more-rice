package com.example.demo.controller;

import com.example.demo.models.linkedlists.AndrewLinkedList;
import consoleUI.ConsoleMethods;
import minilabz.*;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Getter
@Controller
public class AndrewLLController {

    @GetMapping("/andrewLL")
    public String andrewLL(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("andrewRealSort");

        // Benchmarks
        AndrewLinkedList.init();
        long time = System.nanoTime();
        AndrewLinkedList.alInserts();
        long alInsertTime = System.nanoTime() - time;
        String alInsertResult = AndrewLinkedList.ALToString();
        time = System.nanoTime();
        AndrewLinkedList.alDeletes();
        long alDeleteTime = System.nanoTime() - time;
        String alDeleteResult = AndrewLinkedList.ALToString();
        time = System.nanoTime();
        AndrewLinkedList.alSort();
        long alSortTime = System.nanoTime() - time;
        String alSortResult = AndrewLinkedList.ALToString();
        time = System.nanoTime();
        AndrewLinkedList.llInserts();
        long llInsertTime = System.nanoTime() - time;
        String llInsertResult = AndrewLinkedList.LLToString();
        time = System.nanoTime();
        AndrewLinkedList.llDeletes();
        long llDeleteTime = System.nanoTime() - time;
        String llDeleteResult = AndrewLinkedList.LLToString();
        time = System.nanoTime();
        AndrewLinkedList.llSort();
        long llSortTime = System.nanoTime() - time;
        String llSortResult = AndrewLinkedList.LLToString();

        model.addAttribute("Result1", alInsertResult);
        model.addAttribute("Result2", alDeleteResult);
        model.addAttribute("Result3", alSortResult);
        model.addAttribute("Result4", llInsertResult);
        model.addAttribute("Result5", llDeleteResult);
        model.addAttribute("Result6", llSortResult);
        model.addAttribute("Time1", alInsertTime);
        model.addAttribute("Time2", alDeleteTime);
        model.addAttribute("Time3", alSortTime);
        model.addAttribute("Time4", llInsertTime);
        model.addAttribute("Time5", llDeleteTime);
        model.addAttribute("Time6", llSortTime);

        return "andrewLL";

    }

}
