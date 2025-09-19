package com.project.login_page.domain;

import com.sun.source.doctree.EscapeTree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_account;

    @Column(length = 6, nullable = false)
    private int dailyStreak;

    @Column(nullable = false)
    private long minutesStudied;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account(){
        this.dailyStreak = 0;
        this.minutesStudied = 0;
    }
}
