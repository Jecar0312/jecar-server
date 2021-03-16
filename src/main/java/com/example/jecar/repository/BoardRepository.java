package com.example.jecar.repository;

import com.example.jecar.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {
}
