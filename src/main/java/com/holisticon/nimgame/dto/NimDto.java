package com.holisticon.nimgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NimDto {

    private Long id;
    private Long amount;
    private Long turn;
    private String playerName;
}
