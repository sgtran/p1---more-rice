package com.example.demo.controller;
import minilabz.Palindrome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AtharvaPalindromeController {
    @GetMapping("/Palindrome")
    public String greetingForm(Model model) {
        model.addAttribute("Palindrome", new Palindrome());
        return "Palindrome";
    }

    @PostMapping("/Palindrome")
    public String greetingSubmit(@ModelAttribute Palindrome palindrome, Model model, String palintime, String result) {
        model.addAttribute("Palindrome", palindrome);
        palintime = "Palindrome Verification time: " + palindrome.bint + " nanoseconds; O(log n), best-case O(1) if value found at middle index";
           result = Boolean.toString(palindrome.palinverify) + " ";
           model.addAttribute("result", result);
        model.addAttribute("palintime", palintime);
        return "PalinResult";
    }
}
