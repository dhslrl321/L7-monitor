package com.example.l7monitor.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnknownLogTest {

    @Test
    @DisplayName("생성")
    void create() {
        UnknownLog unknownLog = UnknownLog.builder()
                .id(1L)
                .ip("192.168.1.1")
                .data("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko")
                .build();

        assertEquals(1L, unknownLog.getId());
    }

}