package com.example.demo.repository;

import com.example.demo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.util.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
