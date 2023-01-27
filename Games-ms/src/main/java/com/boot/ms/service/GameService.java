package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Game;
import com.boot.ms.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	GameRepository repository;

	public List<Game> getGames() {
		return repository.findAll();
	}

	public Game getGame(int gameCode) {
		return repository.findById(gameCode).orElse(null);
	}
}
