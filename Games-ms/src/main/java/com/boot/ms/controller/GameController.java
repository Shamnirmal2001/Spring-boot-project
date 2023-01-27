package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.boot.ms.entity.Game;
import com.boot.ms.model.FailureResponse;
import com.boot.ms.model.GamePlayerResponse;
import com.boot.ms.model.Player;
import com.boot.ms.service.GameService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/Games")
@RibbonClient(name = "PLAYERS-MS")
public class GameController {

	@Autowired
	RestTemplate template;

	@Autowired
	GameService service;

	@GetMapping("/getGames")
	public ResponseEntity<List<Game>> getGames() {
		return new ResponseEntity<List<Game>>(service.getGames(), HttpStatus.OK);
	}

	@GetMapping("/getGame/{gameCode}")
	public ResponseEntity<?> getGame(@PathVariable int gameCode) {
		if (service.getGame(gameCode) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Game>(service.getGame(gameCode), HttpStatus.OK);
		}
	}

	@GetMapping("/getGameandPlayerbyGameCode/{gameCode}")
	@HystrixCommand(fallbackMethod = "myFallBack")
	public ResponseEntity<?> getGameandPlayerbyGameCode(@PathVariable int gameCode) {
		Game game = service.getGame(gameCode);
		ResponseEntity<?> responseEntity = null;

		if (game == null) {
			responseEntity = new ResponseEntity<String>("no game present", HttpStatus.NOT_FOUND);
		} else {
			List<Player> playerList = template.getForObject("http://PLAYERS-MS/Players/getGameandPlayerbyGameCode/" + game.getGameCode(), List.class);
			GamePlayerResponse response = new GamePlayerResponse();
			response.setGame(game);
			response.setPlayerList(playerList);
			responseEntity = new ResponseEntity<GamePlayerResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}

	public ResponseEntity<?> myFallBack(@PathVariable int gameCode) {

		Game game = service.getGame(gameCode);
		ResponseEntity<?> responseEntity = null;
		FailureResponse response = new FailureResponse();
		response.setGame(game);
		response.setMessage("Players not working");
		responseEntity = new ResponseEntity<FailureResponse>(response, HttpStatus.OK);
		return responseEntity;

	}

}
