package com.example.jecar.api.models;

import com.example.jecar.entity.Board;
import com.example.jecar.entity.Member;
import com.example.jecar.entity.Reply;
import java.time.LocalDateTime;
import java.util.List;

public class APIBoard {
    // 출력을 위한 모델
    // board + reply
    public APIBoard(Board board, List<Reply> replies) {
        this.id = board.getId();
        this.author = board.getAuthor();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createAt = board.getCreateAt();
        this.replies = replies;
    }

    private Integer id;
    private String title;
    private Member author;
    private String content;
    private LocalDateTime createAt;
    private List<Reply> replies;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Member getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public List<Reply> getReplies() {
        return replies;
    }
}
