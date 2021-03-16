package com.example.jecar.dto;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member author;

    @ManyToOne(cascade = CascadeType.ALL)
    private Board board;

    private String content;

    private LocalDateTime createAt;

    @PrePersist
    public void createAt() {
        this.createAt = LocalDateTime.now();
    }
}
