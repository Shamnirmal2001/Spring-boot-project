package com.boot.ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ms.entity.Player;
import com.boot.ms.service.PlayerService;

@RestController
@RequestMapping("/Players")
public class PlayerController {

	@Autowired
	PlayerService service;
	
	@Autowired
	Environment environment;

	@GetMapping("/getPlayers")
	public ResponseEntity<List<Player>> getPlayers() {
		List<Player> playerslist = service.getPlayers();
		List<Player> players = new ArrayList<>();
		
		for(Player player : playerslist) {
			/* player.setPort(environment.getProperty("local.server.port")); */
			players.add(player);
		}
		
		return new ResponseEntity<List<Player>>(service.getPlayers(), HttpStatus.OK);
	}

	@PostMapping("/insertPlayer")
	public ResponseEntity<Player> insertPlayers(@RequestBody Player player) {
		return new ResponseEntity<Player>(service.insertPlayers(player), HttpStatus.OK);
	}

	@PutMapping("/updatePlayer")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
		return new ResponseEntity<Player>(service.updatePlayer(player), HttpStatus.OK);
	}

	@GetMapping("/getPlayer/{playerId}")
	public ResponseEntity<?> getPlayer(@PathVariable int playerId) {
		if (service.getPlayer(playerId) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Player>(service.getPlayer(playerId), HttpStatus.OK);
		}
	}

	@DeleteMapping("/deletePlayerByAge/{num}")
	public ResponseEntity<List<Player>> deletePlayerByAge(@PathVariable int num) {
		service.deletePlayerByAge(num);
		List<Player> players = new ArrayList<>();
		service.getPlayers();

		return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
	}

	@GetMapping("/getGameandPlayerbyGameCode/{gameCode}")
	public ResponseEntity<?> getGameandPlayerbyGameCode(@PathVariable int gameCode) {

		List<Player> playerList = service.getGameandPlayerbyGameCode(gameCode);
		ResponseEntity<?> responseEntity = null;

		if (playerList == null) {
			responseEntity = new ResponseEntity<String>("No Players present", HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
		}

		return responseEntity;
	}

}
