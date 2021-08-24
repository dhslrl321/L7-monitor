package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.ThreatLogCountResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.AbnormalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ThreatServiceTest {
    private ThreatService threatService;

    private final AbnormalRepository abnormalRepository = mock(AbnormalRepository.class);

    @BeforeEach
    void setUp() {
        threatService = new ThreatService(abnormalRepository);

        LocalDateTime now = LocalDateTime.now();

        given(abnormalRepository.countByMalCodeCodeAndTimestampBetween(
                eq(1), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(4L);

        given(abnormalRepository.countByMalCodeCodeAndTimestampBetween(
                eq(2), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(5L);

        given(abnormalRepository.countByMalCodeCodeAndTimestampBetween(
                eq(3), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(6L);

        given(abnormalRepository.countByMalCodeCodeAndTimestampBetween(
                eq(4), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(7L);
    }

    @Test
    @DisplayName("getAllThreatLog - 성공")
    void getAllThreatLog() {
        ThreatLogCountResponse response = threatService.getAllThreatLogCount();

        assertAll(
                () -> assertEquals(4, response.getSqli().getCount()),
                () -> assertEquals(5, response.getWshell().getCount()),
                () -> assertEquals(6, response.getRfi().getCount()),
                () -> assertEquals(7, response.getXss().getCount())
        );
    }
}