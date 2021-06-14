package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.models.linkedlists.AndrewLinkedList;
import consoleUI.ConsoleMethods;
import minilabz.*;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static com.example.demo.controller.scoreModifier.*;

@Getter
@Controller
public class LeaderboardController {
    @GetMapping("/leaderboard")
    public String search(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("leaderboard");

        String[] topPlayers = topPlayers();
        int[] topScores = topScores();

        for (int i = 0; i < 10; i++) {
            String strName = "name" + i;
            String strScore = "score" + i;
            model.addAttribute(strName, topPlayers[i]);
            model.addAttribute(strScore, topScores[i]);
        }

        return "leaderboard";

    }

}
