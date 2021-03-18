package com.example.jecar.service;

import com.example.jecar.dto.Board;
import com.example.jecar.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Integer id) {
        return boardRepository.findById(id).get();
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public void delete(Board board) {
        boardRepository.delete(board);
    }
}

