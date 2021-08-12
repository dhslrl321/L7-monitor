package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.LogResponseData;
import com.example.l7monitor.domain.dto.UnknownLogResponseData;
import com.example.l7monitor.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LogController.class)
class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogService logService;

    @BeforeEach
    void setUp() {

        List<LogResponseData> allLogs = List.of(
                LogResponseData.builder().ip("192.168.0.1").timestamp(LocalDateTime.now()).method("GET").build(),
                LogResponseData.builder().ip("192.168.0.1").timestamp(LocalDateTime.now()).method("POST").build(),
                LogResponseData.builder().ip("192.168.0.1").timestamp(LocalDateTime.now()).method("DELETE").build()
        );

        List<UnknownLogResponseData> allUnknownLogs = List.of(
                UnknownLogResponseData.builder().ip("192.168.0.1").data("/url").build(),
                UnknownLogResponseData.builder().ip("192.168.0.1").data("/url").build(),
                UnknownLogResponseData.builder().ip("192.168.0.1").data("/url").build(),
                UnknownLogResponseData.builder().ip("192.168.0.1").data("/url").build()
        );

        given(logService.getAllLog()).willReturn(allLogs);

        given(logService.getAllUnknownLog()).willReturn(allUnknownLogs);
    }

    @Test
    @DisplayName("모든 로그 확인")
    void getAllLog() throws Exception {
        mockMvc.perform(get("/api/logs/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].ip").exists())
                .andExpect(jsonPath("[0].timestamp").exists())
                .andExpect(jsonPath("[0].method").exists());
    }

    @Test
    @DisplayName("모든 미확인로그 확인")
    void getAllUnknownLog () throws Exception {
        mockMvc.perform(get("/api/logs/unknown")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].ip").exists())
                .andExpect(jsonPath("[0].data").exists());
    }

}