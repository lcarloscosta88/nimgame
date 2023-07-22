package com.holisticon.nimgame.controller;

import com.holisticon.nimgame.dto.NimDto;
import com.holisticon.nimgame.exception.AmountException;
import com.holisticon.nimgame.service.NimService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class NimControllerTest {

    @Mock
    private NimService nimService;

    @InjectMocks
    private NimController nimController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInputNumberValidInput() throws Exception {
        long amount = 5L;
        NimDto nimDto = new NimDto();

        when(nimService.removeMatches(amount)).thenReturn(nimDto);

        ResponseEntity<Object> response = nimController.inputNumber(amount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInputNumberInvalidInput() throws Exception {
        long amount = 0L;
        String errorMessage = "Invalid amount";

        when(nimService.removeMatches(anyLong())).thenThrow(new AmountException(errorMessage));

        ResponseEntity<Object> response = nimController.inputNumber(amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
}
