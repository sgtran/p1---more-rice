package com.example.demo.controller;
import com.example.demo.models.linkedlists.CircleQueue;
import lombok.Getter;

import com.example.demo.models.User;
import com.example.demo.service.CustomUserDetailsService;
import minilabz.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Validated User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = {"/uno"}, method = RequestMethod.GET)
    public ModelAndView uno() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uno");
        return modelAndView;
    }

    @RequestMapping(value = {"/cards"}, method = RequestMethod.GET)
    public ModelAndView cards() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cards");
        return modelAndView;
    }

    @RequestMapping(value = {"/minilabTest"}, method = RequestMethod.GET)
    public ModelAndView minilabTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("minilabTest");
        return modelAndView;
    }

    @GetMapping("/test")
    public String factorialD(@RequestParam(name = "value", required = false) String value, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        long InputVal;

        try {
            InputVal = Long.parseLong(value);
        } catch (Exception Ex) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "test";
        }

        if (InputVal < 0 || InputVal > 20) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "test";
        }

        // Benchmarks
        long StartRec = System.nanoTime();
        long Result = minilabz.Factorial.Recursion(InputVal);
        long EndRec = System.nanoTime();
        long Result2 = minilabz.Factorial.ForLoop(InputVal);
        long EndForLoop = System.nanoTime();
        long Result3 = minilabz.Factorial.Stream(InputVal);
        long EndStream = System.nanoTime();

        model.addAttribute("result", Result);
        model.addAttribute("input", InputVal);
        model.addAttribute("recursionTime", (EndRec - StartRec) / 1000000.0);
        model.addAttribute("forLoopTime", (EndForLoop - EndRec) / 1000000.0);
        model.addAttribute("streamTime", (EndStream - EndForLoop) / 1000000.0);

        return "test";
    }


    @GetMapping("/fibonacci")
    public String fibonacciD(@RequestParam(name="fibseq", required=false,  defaultValue="2") String fibseq, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fibonacci");
        long InputVal;

        try {
            InputVal = Long.parseLong(fibseq);
        } catch (Exception Ex) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "test";
        }

        if (InputVal < 0 || InputVal > 20) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "test";
        }

        // Benchmarks
        long StartRec = System.nanoTime();
        minilabz.Fibonacci.Recursion(InputVal, 0, 1);
        long EndRec = System.nanoTime();
        minilabz.Fibonacci.For(InputVal, 0, 1);
        long EndForLoop = System.nanoTime();
        inilabz.Fibonacci.While(InputVal, 0, 1);
        long EndWhileLoop = System.nanoTime();
        String Result = minilabz.Fibonacci.RecResult();
        String Result2 = minilabz.Fibonacci.ForResult();
        String Result3 = minilabz.Fibonacci.WhileResult();

        model.addAttribute("result", Result);
        model.addAttribute("input", InputVal);
        model.addAttribute("recurseTime", (EndRec - StartRec) / 1000000.0);
        model.addAttribute("forTime", (EndForLoop - EndRec) / 1000000.0);
        model.addAttribute("whileTime", (EndWhileLoop - EndForLoop) / 1000000.0);

        return "fibonacci";

    }

    @GetMapping("/GCD")
    public String GCD(@RequestParam(name = "value", required = false) String value,
                      @RequestParam(name = "value2",required = false, defaultValue = "1") String value2,
                      Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("GCD");
        long InputVal;

        try {
            InputVal = Long.parseLong(value);
        } catch (Exception Ex) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "GCD";
        }

        if (InputVal < 0 || InputVal > 20) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "GCD";
        }
        long InputVal2;

        try {
            InputVal2 = Long.parseLong(value2);
        } catch (Exception Ex) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "GCD";
        }

        if (InputVal2 < 0 || InputVal2 > 20) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "GCD";
        }

        // Benchmarks
        long Result = minilabz.GCD.gcd(InputVal, InputVal2);
        long StartRec = System.nanoTime();
        long EndRec = System.nanoTime();




        model.addAttribute("result", Result);
        model.addAttribute("input", InputVal);
        model.addAttribute("input2", InputVal2);
        model.addAttribute("recursionTime", (EndRec - StartRec) / 1000000.0);



        return "GCD";
    }

}
