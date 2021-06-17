
package com.example.demo.controller;

import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.PlayerService;
import com.example.demo.util.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/player/get")
    public List<Player> fetchAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/player/post")
    public ResponseEntity<Object> createPlayer(@RequestParam("name") String name,
                                               @RequestParam("score") String scoreString) {

        int score;

        try {
            score = Integer.parseInt(scoreString);
        } catch (Exception e){
            return new ResponseEntity<>(scoreString +" error; Input a valid integer", HttpStatus.BAD_REQUEST);
        }

        Player player = new Player(name, score);
        playerService.savePlayer(player);
        return new ResponseEntity<>(name +" is created successfully", HttpStatus.CREATED);

    }

}


