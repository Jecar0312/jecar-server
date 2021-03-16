package com.example.jecar.service;

import com.example.jecar.dto.Reply;
import com.example.jecar.repository.BoardRepository;
import com.example.jecar.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(BoardRepository boardRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }

    public List<Reply> findAllByBoard(Integer id) { // 모든 reply들 중에서 id가 일치하는 것들
        List<Reply> replies = new ArrayList<>();
        List<Reply> all = replyRepository.findAll();

        for (Reply reply:all) {
            if (reply.getBoard().getId().equals(id)) {
                replies.add(reply);
            }
        }
        return replies;
    }

    public Reply findById(Integer id) {
        return replyRepository.findById(id).get();
    }

    public List<Reply> findALl() {
        return replyRepository.findAll();
    }

    public Reply save(Reply reply) {
        return replyRepository.save(reply);
    }

    public void delete(Integer id) {
        replyRepository.delete(findById(id));
    }
}
