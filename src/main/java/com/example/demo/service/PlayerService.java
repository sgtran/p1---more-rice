package com.example.demo.service;

import com.example.demo.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.util.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public void savePlayer(Player p) {
        playerRepository.save(p);
        return;
    }

}
