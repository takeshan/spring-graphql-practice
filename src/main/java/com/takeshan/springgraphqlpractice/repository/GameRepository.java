package com.takeshan.springgraphqlpractice.repository;

import com.takeshan.springgraphqlpractice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByCompanyName(String companyName);
}
