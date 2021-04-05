package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.CustomUserDetailsService;
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

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
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

    @RequestMapping(value = {"/Factorial"}, method = RequestMethod.GET)
    public String factorialD(@RequestParam(name = "value", required = false) String value, Model model) {

        long InputVal;
        try {
            InputVal = Long.parseLong(value);
        } catch (Exception Ex) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "Recursion/Factorial";
        }

        if (InputVal < 0 || InputVal > 20) {
            model.addAttribute("error", "Please input a valid integer between 0 and 20.");
            return "Recursion/Factorial";
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

        return "Recursion/Factorial";
    }



}
