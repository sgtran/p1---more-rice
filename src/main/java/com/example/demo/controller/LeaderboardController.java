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

@Getter
@Controller
public class LeaderboardController {
    @GetMapping("/leaderboard")
    public String search(@RequestParam(name="username", required=false,  defaultValue="Test") String username, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("leaderboard");

        ArrayList<String> topPlayers = new ArrayList<>();
        ArrayList<Integer> topScores = new ArrayList<>();
        topPlayers.add("Abraham");
        topPlayers.add("Bryant");
        topPlayers.add("Charlie");
        topPlayers.add("Deanna");
        topPlayers.add("Eric");
        topPlayers.add("Flora");
        topPlayers.add("Gaurish");
        topPlayers.add("Hannah");
        topPlayers.add("I");
        topPlayers.add("Julia");
        topPlayers.add("Test");
        topPlayers.add("Easter Egg");
        for (Integer i = 1000; i >=0; i -= 100) {
            topScores.add(i);
        }
        topScores.add(420);

        int index = -1;
        for (int i = 0; i < topPlayers.size(); i++) {
            if (topPlayers.get(i).equals(username)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            model.addAttribute("result", "Score: " + topScores.get(index));
        } else {
            model.addAttribute("result", "No Score Data ");
        }

        model.addAttribute("name1", topPlayers.get(0));
        model.addAttribute("name2", topPlayers.get(1));
        model.addAttribute("name3", topPlayers.get(2));
        model.addAttribute("name4", topPlayers.get(3));
        model.addAttribute("name5", topPlayers.get(4));
        model.addAttribute("name6", topPlayers.get(5));
        model.addAttribute("name7", topPlayers.get(6));
        model.addAttribute("name8", topPlayers.get(7));
        model.addAttribute("name9", topPlayers.get(8));
        model.addAttribute("name0", topPlayers.get(9));
        model.addAttribute("score1", topScores.get(0));
        model.addAttribute("score2", topScores.get(1));
        model.addAttribute("score3", topScores.get(2));
        model.addAttribute("score4", topScores.get(3));
        model.addAttribute("score5", topScores.get(4));
        model.addAttribute("score6", topScores.get(5));
        model.addAttribute("score7", topScores.get(6));
        model.addAttribute("score8", topScores.get(7));
        model.addAttribute("score9", topScores.get(8));
        model.addAttribute("score0", topScores.get(9));


        return "leaderboard";

    }

}
