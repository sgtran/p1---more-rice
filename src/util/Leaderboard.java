package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

@Controller
public class Leaderboard {

    ArrayList<Player> leaderboard;

    public Leaderboard() {
        //Temporary leaderboard placeholders
        Player Steven = new Player("Steven");
        Steven.addScore(100);
        leaderboard.add(Steven);
        Player Marshall = new Player("Marshall");
        Marshall.addScore(90);
        leaderboard.add(Marshall);
        Player Charlie = new Player("Charlie");
        Charlie.addScore(80);
        leaderboard.add(Charlie);
        Player Janet = new Player("Janet");
        Janet.addScore(70);
        leaderboard.add(Janet);
        Player Cammie = new Player("Cammie");
        Cammie.addScore(60);
        leaderboard.add(Cammie);
        Player Gerald = new Player("Gerald");
        Gerald.addScore(50);
        leaderboard.add(Gerald);
        Player Stanley = new Player("Stanley");
        Stanley.addScore(40);
        leaderboard.add(Stanley);
        Player Taylor = new Player("Taylor");
        Taylor.addScore(30);
        leaderboard.add(Taylor);
        Player Carla = new Player("Carla");
        Carla.addScore(20);
        leaderboard.add(Carla);
        Player Bo = new Player("Bo");
        Bo.addScore(10);
        leaderboard.add(Bo);
    }

    public void updateLeaderboard(Player winner) {

        int index = -1;

        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).getName().equals(winner.getName())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            leaderboard.add(winner);
            index = leaderboard.size() - 1;
        }

        for (int i = index; i > 0; i--) {
            if (leaderboard.get(i).getScore() > leaderboard.get(i-1).getScore()) {
                Player temp = leaderboard.get(i-1);
                leaderboard.set(i - 1, leaderboard.get(i));
                leaderboard.set(i, temp);
            } else {
                leaderboard.remove(10);
                break;
            }
        }
    }

    @GetMapping("/leaderboard")
    public String leaders() {
        model.addAttribute("leaderboard", getLeaderboard());
        return "leaderboard";
    }

    public ArrayList<Player> getLeaderboard() { return leaderboard(); }

}
