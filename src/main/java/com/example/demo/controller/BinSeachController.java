package com.example.demo.controller;
import com.example.demo.models.binSeach;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BinSeachController {
    @GetMapping("/binSearch")
    public String greetingForm(Model model) {
        model.addAttribute("binSeach", new binSeach());
        return "binSearch";
    }

    @PostMapping("/binSearch")
    public String greetingSubmit(@ModelAttribute binSeach binseach, Model model, String bintime, String lintime) {
        model.addAttribute("binSeach", binseach);
        bintime = "Binary search time: " + binseach.bint + " nanoseconds";
        lintime = "Linear search time: " + binseach.lint + " nanoseconds";

        model.addAttribute("bintime", bintime);
        model.addAttribute("lintime", lintime);
        return "binResult";
    }
}
