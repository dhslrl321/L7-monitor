package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.ThreatLogCountResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.service.ThreatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThreatController.class)
class ThreatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ThreatService threatService;

    @BeforeEach
    void setUp() {
        TotalTrafficResponse todayThreatTraffic = TotalTrafficResponse.builder()
                .id(1L)
                .timestamp(LocalDateTime.now().minusDays(14L))
                .count(821L)
                .build();

        ThreatLogCountResponse threatLogCountResponse = ThreatLogCountResponse.builder()
                .sqli(todayThreatTraffic)
                .rfi(todayThreatTraffic)
                .wshell(todayThreatTraffic)
                .xss(todayThreatTraffic)
                .build();

        given(threatService.getAllThreatLogCount()).willReturn(threatLogCountResponse);
    }

    @Test
    @DisplayName("공격 유형에 따른 14일 전 요청 카운트")
    void getAllThreatLogCount() throws Exception {
        mockMvc.perform(get("/api/threats"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("xss").exists())
                .andExpect(jsonPath("rfi").exists())
                .andExpect(jsonPath("wshell").exists())
                .andExpect(jsonPath("sqli").exists())
        ;
    }

}