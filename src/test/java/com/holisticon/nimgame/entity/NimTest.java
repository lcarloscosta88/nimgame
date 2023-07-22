package com.holisticon.nimgame.entity;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NimTest {

    @Test
    public void testAttributesTypes() {
        Field[] fields = Nim.class.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();

            if (fieldName.equals("id")) {
                assertEquals(Long.class, field.getType());
            } else if (fieldName.equals("amount") || fieldName.equals("turn")) {
                assertEquals(Long.class, field.getType());
            } else if (fieldName.equals("playerName")) {
                assertEquals(String.class, field.getType());
            } else {
                assertTrue(false, "Unexpected field: " + fieldName);
            }
        }
    }
}
