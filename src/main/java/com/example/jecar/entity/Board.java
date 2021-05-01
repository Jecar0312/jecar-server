package com.example.jecar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member author;

    private String content;

    private LocalDateTime createAt;

    @PrePersist
    public void createAt() {
        this.createAt = LocalDateTime.now();
    }
}
