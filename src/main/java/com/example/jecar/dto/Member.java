package com.example.jecar.dto;

import antlr.collections.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;


@Data
@Entity(name = "member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String password;
    private String mail;
    private boolean enabled;
//    private List<GrantedAuthority> authorities;


    public Member(String username, String name, String password, String mail) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.enabled = true;
    }

    private LocalDateTime createAt;

    public Member() {

    }

    @PrePersist
    public void createAt() {
        this.createAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}
