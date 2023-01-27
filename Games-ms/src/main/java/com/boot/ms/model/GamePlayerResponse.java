package com.boot.ms.model;

import java.util.List;

import com.boot.ms.entity.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayerResponse {

	private Game game;
	private List<Player> playerList;

}
