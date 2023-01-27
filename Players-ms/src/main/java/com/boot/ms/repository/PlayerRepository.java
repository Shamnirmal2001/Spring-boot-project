package com.boot.ms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

	public List<Player> findAllByGameCode(int gameCode);
	
	
	@Transactional
	@Modifying
	@Query(name = "deletePlayerByAge", value = "delete from Player p where p.playerAge<?1")
	public void deletePlayerByAge(int num);
	
}
