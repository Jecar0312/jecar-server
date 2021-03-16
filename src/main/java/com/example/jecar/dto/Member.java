package com.example.jecar.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account;
    private String name;
    private String password;
    private String mail;

    private LocalDateTime createAt;

    @PrePersist
    public void createAt() {
        this.createAt = LocalDateTime.now();
    }
}