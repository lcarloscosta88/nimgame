package com.holisticon.nimgame.service;

import com.holisticon.nimgame.dto.NimDto;
import com.holisticon.nimgame.entity.Nim;
import com.holisticon.nimgame.exception.AmountException;
import com.holisticon.nimgame.repository.NimRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NimServiceTest {

    @Mock
    private NimRepository nimRepository;

    @InjectMocks
    private NimService nimService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveMatchesValidInput() throws Exception {
        Long initialTotalMatches = 13L;
        Long amount = 3L;
        Nim nim = new Nim(amount, 1L, "Player");
        NimDto expectedNimDto = new NimDto();
        BeanUtils.copyProperties(nim, expectedNimDto);

        when(nimRepository.save(any(Nim.class))).thenReturn(nim);

        NimDto result = nimService.removeMatches(amount);

        Long expectedTotalMatches = initialTotalMatches - amount;

        assertEquals(expectedTotalMatches, getTotalMatches());
        assertEquals(expectedNimDto, result);
    }

    @Test
    public void testRemoveMatchesInvalidInput() {
        Long amount = 0L;
        assertThrows(AmountException.class, () -> nimService.removeMatches(amount));
    }

    @Test
    public void testRemoveMatchesInvalidAmountExceedsTotalMatches() {
        Long amount = 14L;

        assertThrows(AmountException.class, () -> nimService.removeMatches(amount));
    }

    private long getTotalMatches() {
        try {
            Field totalMatchesField = NimService.class.getDeclaredField("totalMatches");
            totalMatchesField.setAccessible(true);
            return (long) totalMatchesField.get(nimService);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
