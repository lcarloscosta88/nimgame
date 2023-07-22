package com.holisticon.nimgame.service;

import com.holisticon.nimgame.dto.NimDto;
import com.holisticon.nimgame.entity.Nim;
import com.holisticon.nimgame.exception.AmountException;
import com.holisticon.nimgame.repository.NimRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class NimService {
    private Long totalMatches;
    private Long turnCounter;
    private final NimRepository nimRepository;

    @Autowired
    public NimService(NimRepository nimRepository) {
        totalMatches = 13L;
        turnCounter = 1L;
        this.nimRepository = nimRepository;
    }

    public NimDto removeMatches(Long amount) throws Exception {
        try {
            log.info("> NimService.removeMatches | Removal attempt with {} ", amount);
            NimDto nimDto = new NimDto();

            if (amount < 1 || amount > 3) {
                log.error("> NimService.removeMatches | Removal attempt with less than 1 and more than 3");
                throw new AmountException("Invalid amount. Please choose a number between 1 and 3.");
            }

            if (amount > totalMatches) {
                log.error("> NimService.removeMatches | Attempted removal with more matchsticks than remaining total");
                throw new AmountException("Invalid amount. Please choose a smaller quantity.");
            }

            totalMatches -= amount;
            Nim nim = new Nim(amount, turnCounter, "Player");
            nimRepository.save(nim);
            BeanUtils.copyProperties(nim, nimDto);
            log.info("> NimService.removeMatches | User removed {} matches. Total matches left: {}", amount, totalMatches);

            if (!isGameOver()) {
                turnCounter++;
                playComputerTurn();
            }

            return nimDto;
        } catch (Exception ex){
            throw new Exception(ex);
        }

    }

    private void playComputerTurn() {
        Long maxQuantity = Math.min(3, totalMatches); // Maximum quantity should not exceed the total matches left
        Random random = new Random();
        Long amount = random.nextLong(maxQuantity) + 1; // Generates a random quantity between 1 and maxQuantity

        totalMatches -= amount;
        Nim nim = new Nim(amount, turnCounter, "Computer");
        nimRepository.save(nim);
        log.info("> NimService.removeMatches | User removed {} matches. Total matches left: {}", amount, totalMatches);

        if (isGameOver()) {
            System.out.println("Oops! The computer won!");
        }

        turnCounter++; // Increment turn counter
    }

    public boolean isGameOver() {
        return totalMatches == 0;
    }

    public Long getTurnsTaken() {
        return turnCounter;
    }
}
