package com.example.l7monitor.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AbnormalEntityTest {

    @Test
    void create() {
        long id = 1004L;
        String ip = "192.168.0.1";
        int malCode = 100;
        String uri = "/page?type=<script>alert(1)</script>";
        LocalDateTime time = LocalDateTime.now();

        Abnormal abnormal = Abnormal.builder()
                .id(id)
                .ip(ip)
                .timestamp(time)
                .uri(uri)
                .malCode(malCode)
                .build();

        assertAll(
                () -> assertEquals(id, abnormal.getId()),
                () -> assertEquals(ip, abnormal.getIp()),
                () -> assertEquals(malCode, abnormal.getMalCode()),
                () -> assertEquals(uri, abnormal.getUri()),
                () -> assertEquals(time, abnormal.getTimestamp())
        );
    }

}