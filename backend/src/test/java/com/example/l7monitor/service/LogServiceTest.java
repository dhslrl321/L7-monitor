package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.LogResponseData;
import com.example.l7monitor.domain.dto.UnknownLogResponseData;
import com.example.l7monitor.domain.entity.Total;
import com.example.l7monitor.domain.entity.UnknownLog;
import com.example.l7monitor.domain.repository.TotalRepository;
import com.example.l7monitor.domain.repository.UnknownLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LogServiceTest {

    private LogService logService;

    private final TotalRepository totalRepository = mock(TotalRepository.class);
    private final UnknownLogRepository unknownLogRepository = mock(UnknownLogRepository.class);

    @BeforeEach
    void setUp() {

        logService = new LogService(totalRepository, unknownLogRepository, new ModelMapper());

        List<Total> totals = List.of(Total.builder().id(1L).ip("192.168.0.1").timestamp(LocalDateTime.now()).build(),
                Total.builder().id(2L).ip("192.168.0.1").timestamp(LocalDateTime.now()).build(),
                Total.builder().id(3L).ip("192.168.0.1").timestamp(LocalDateTime.now()).build(),
                Total.builder().id(4L).ip("192.168.0.1").timestamp(LocalDateTime.now()).build(),
                Total.builder().id(5L).ip("192.168.0.1").timestamp(LocalDateTime.now()).build());

        List<UnknownLog> unknownLogs = List.of(UnknownLog.builder().id(1L).ip("192.168.0.1").data("/url").build(),
                UnknownLog.builder().id(2L).ip("192.168.0.1").data("/url").build(),
                UnknownLog.builder().id(3L).ip("192.168.0.1").data("/url").build(),
                UnknownLog.builder().id(4L).ip("192.168.0.1").data("/url").build());

        given(totalRepository.findTop100ByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(totals);

        given(unknownLogRepository.findAll()).willReturn(unknownLogs);
    }

    @Test
    @DisplayName("-24 시간 내의 모든 로그 확인")
    void getAllLog() {
        List<LogResponseData> logs = logService.getAllLog();

        assertEquals(5, logs.size());
    }

    @Test
    @DisplayName("모든 unknown 로그 확인")
    void getAllUnknownLog() {
        List<UnknownLogResponseData> logs = logService.getAllUnknownLog();

        assertEquals(4, logs.size());
    }
}