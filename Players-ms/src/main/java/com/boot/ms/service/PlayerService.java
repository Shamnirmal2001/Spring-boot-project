package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Player;
import com.boot.ms.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository repository;

	public List<Player> getPlayers() {
		return repository.findAll();
	}

	public Player insertPlayers(Player player) {
		return repository.save(player);

	}

	public Player getPlayer(int playerId) {
		return repository.findById(playerId).orElse(null);
	}

	public Player updatePlayer(Player player) {
		Player playerdata = repository.findById(player.getPlayerId()).get();
		playerdata.setPlayerName(player.getPlayerName());
		playerdata.setPlayerAge(player.getPlayerAge());
		return repository.save(playerdata);
	}

	public void deletePlayerByAge(int num) {
		repository.deletePlayerByAge(num);
	}

	public List<Player> getGameandPlayerbyGameCode(int gameCode) {
		return repository.findAllByGameCode(gameCode);
	}
}
