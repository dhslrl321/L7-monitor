package com.example.l7monitor.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MalCodeTest {

    @Test
    @DisplayName("객체 생성 테스트")
    void create() {
        MalCode sqlInjection = MalCode.builder()
                .code(1)
                .malName("SQL Injection")
                .build();

        MalCode xss = new MalCode(2, "XSS");

        assertEquals(1, sqlInjection.getCode());
        assertEquals("SQL Injection", sqlInjection.getMalName());
        assertEquals(2, xss.getCode());
    }

}