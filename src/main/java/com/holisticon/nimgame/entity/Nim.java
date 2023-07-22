package com.holisticon.nimgame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Nim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "turn")
    private Long turn;

    @Column(name="player_name")
    private String playerName;

    public Nim(Long amount, Long turn, String playerName) {
        this.amount = amount;
        this.turn = turn;
        this.playerName = playerName;
    }
}
