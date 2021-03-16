package com.example.jecar.controller;

import com.example.jecar.api.models.APIBoard;
import com.example.jecar.dto.Board;
import com.example.jecar.dto.Reply;
import com.example.jecar.repository.BoardRepository;
import com.example.jecar.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. Board"})
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
    @ApiOperation(value = "게시글 조회", notes = "모든 게시글을 조회한다.")
    @GetMapping("/")
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @ApiOperation(value = "단일 게시글 조회", notes = "단일 게시글과 해당 게시글에 달린 댓글들을 조회한다.")
    @GetMapping("/{id}")
    public APIBoard get(@PathVariable Integer id) {
        Board board = boardRepository.findById(id).get();
        List<Reply> replyList = replyService.findAllByBoard(id);
        return new APIBoard(board, replyList);
    }

    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    @PostMapping("/")
    public Board create(@ModelAttribute Board board) {
        return boardRepository.save(board);
    }

    @ApiOperation(value = "게시글 삭제", notes = "해당하는 게시글을 삭제한다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        boardRepository.delete(boardRepository.findById(id).get());
    }

    @ApiOperation(value = "게시글 수정", notes = "해당하는 게시글을 수정한다.")
    @PutMapping("/{id}")
    public Board modify(@PathVariable Integer id, @ModelAttribute final Board updates) {
        Board board = boardRepository.findById(id).get();
        if (updates.getTitle() != null) board.setTitle(updates.getTitle());
        if (updates.getContent() != null) board.setContent(updates.getContent());
        return boardRepository.save(board);
    }
    /////

    ///// Reply
    @ApiOperation(value = "댓글 생성", notes = "댓글을 생성한다.")
    @PostMapping("/{boardId}/reply")
    public Reply createReply(@ModelAttribute Reply reply, @PathVariable Integer boardId) {
        reply.setBoard(boardRepository.findById(boardId).get());
        return replyService.save(reply);
    }
    /////
}
