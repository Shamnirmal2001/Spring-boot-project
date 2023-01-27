package com.boot.ms.model;

import com.boot.ms.entity.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailureResponse {
	
	private Game game;
	private String message;

}