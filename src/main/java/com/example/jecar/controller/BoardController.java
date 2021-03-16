package com.example.jecar.controller;

import com.example.jecar.api.models.APIBoard;
import com.example.jecar.dto.Board;
import com.example.jecar.dto.Reply;
import com.example.jecar.repository.BoardRepository;
import com.example.jecar.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boards")
public class BoardController {

    //Board CRUD, Reply CRUD

    private final BoardRepository boardRepository;
    private final ReplyService replyService;

    @Autowired
    public BoardController(BoardRepository boardRepository, ReplyService replyService) {
        this.boardRepository = boardRepository;
        this.replyService = replyService;
    }

    ///// Board
    @GetMapping("/")
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @GetMapping("/{id}")
    public APIBoard get(@PathVariable Integer id) {
        Board board = boardRepository.findById(id).get();
        List<Reply> replyList = replyService.findAllByBoard(id);
        return new APIBoard(board, replyList);
    }

    @PostMapping("/")
    public Board create(@ModelAttribute Board board) {
        return boardRepository.save(board);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        boardRepository.delete(boardRepository.findById(id).get());
    }

    @PutMapping("/{id}")
    public Board modify(@PathVariable Integer id, @ModelAttribute final Board updates) {
        Board board = boardRepository.findById(id).get();
        if (updates.getTitle() != null) board.setTitle(updates.getTitle());
        if (updates.getContent() != null) board.setContent(updates.getContent());
        return boardRepository.save(board);
    }
    /////

    ///// Reply
    @PostMapping("/{boardId}/reply")
    public Reply createReply(@ModelAttribute Reply reply, @PathVariable Integer boardId) {
        reply.setBoard(boardRepository.findById(boardId).get());
        return replyService.save(reply);
    }
    /////
}
