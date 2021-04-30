package com.example.jecar.controller;

import com.example.jecar.api.models.APIBoard;
import com.example.jecar.dto.Board;
import com.example.jecar.dto.Reply;
import com.example.jecar.service.BoardService;
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

    private final BoardService boardService;
    private final ReplyService replyService;

    @Autowired
    public BoardController(BoardService boardService, ReplyService replyService) {
        this.boardService = boardService;
        this.replyService = replyService;
    }

    ///// Board
    @ApiOperation(value = "게시글 조회", notes = "모든 게시글을 조회한다.")
    @GetMapping("/")
    public List<Board> list() {
        return boardService.findAll();
    }

    @ApiOperation(value = "단일 게시글 조회", notes = "단일 게시글과 해당 게시글에 달린 댓글들을 조회한다.")
    @GetMapping("/{id}")
    public APIBoard get(@PathVariable Integer id) {
        Board board = boardService.findById(id);
        List<Reply> replyList = replyService.findAllByBoardId(id);
        return new APIBoard(board, replyList);
    }

    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    @PostMapping("/")
    public Board create(@ModelAttribute Board board) {
        return boardService.save(board);
    }

    @ApiOperation(value = "게시글 삭제", notes = "해당하는 게시글을 삭제한다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        boardService.delete(boardService.findById(id));
    }

    @ApiOperation(value = "게시글 수정", notes = "해당하는 게시글을 수정한다.")
    @PutMapping("/{id}")
    public Board modify(@PathVariable Integer id, @ModelAttribute final Board updates) {
        Board board = boardService.findById(id);
        if (updates.getTitle() != null) board.setTitle(updates.getTitle());
        if (updates.getContent() != null) board.setContent(updates.getContent());
        return boardService.save(board);
    }
    /////

    ///// Reply
    @ApiOperation(value = "댓글 생성", notes = "댓글을 생성한다.")
    @PostMapping("/{boardId}/reply")
    public Reply createReply(@ModelAttribute Reply reply, @PathVariable Integer boardId) {
        reply.setBoard(boardService.findById(boardId));
        return replyService.save(reply);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    @PutMapping("/{boardId}/reply/{replyId}")
    public Reply modifyReply(@PathVariable Integer replyId, @ModelAttribute final Reply updates) {
        Reply reply = replyService.findById(replyId);
        if (updates.getContent()!=null) reply.setContent(reply.getContent());
        return replyService.save(reply);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    @DeleteMapping("/{boardId}/reply/{replyId}")
    public void deleteReply(@PathVariable Integer boardId, @PathVariable Integer replyId) {
        replyService.delete(replyId);
    }
    /////
}
