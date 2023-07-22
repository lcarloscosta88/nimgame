package com.holisticon.nimgame.controller;

import com.holisticon.nimgame.exception.AmountException;
import com.holisticon.nimgame.service.NimService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nim")
public class NimController {

    private NimService nimService;

    @Autowired
    public NimController(NimService nimService) {
        this.nimService = nimService;
    }

    @PostMapping("/{amount}")
    @Operation(summary = "Receive a number")
    public ResponseEntity<Object> inputNumber(@PathVariable Long amount){
        try {
            return ResponseEntity.ok(nimService.removeMatches(amount));
        } catch (AmountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
