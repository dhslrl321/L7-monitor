package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.types.PeriodType;
import com.example.l7monitor.domain.types.TrafficType;
import com.example.l7monitor.service.TrafficService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrafficController.class)
class TrafficControllerTest {


    private static final List<TotalTrafficResponse> WEEK = generateTotalTrafficResponseByType(PeriodType.WEEK);
    private static final List<TotalTrafficResponse> DAY = generateTotalTrafficResponseByType(PeriodType.DAY);
    private static final List<TotalTrafficResponse> FIVE_MINUTE = generateTotalTrafficResponseByType(PeriodType.FIVE_MINUTE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrafficService trafficService;

    @BeforeEach
    void setUp() {

        TotalTrafficResponse todayAllTraffic = TotalTrafficResponse.builder()
                .id(1L)
                .timestamp(LocalDateTime.now().minusDays(1))
                .count(78921L)
                .build();

        TotalTrafficResponse todayThreatTraffic = TotalTrafficResponse.builder()
                .id(1L)
                .timestamp(LocalDateTime.now().minusDays(1))
                .count(821L)
                .build();

        given(trafficService.getTrafficCountByPeriod(PeriodType.WEEK))
                .willReturn(WEEK);

        given(trafficService.getTrafficCountByPeriod(PeriodType.DAY))
                .willReturn(DAY);

        given(trafficService.getTrafficCountByPeriod(PeriodType.FIVE_MINUTE))
                .willReturn(FIVE_MINUTE);

        given(trafficService.getTodayTrafficSummaries(TrafficType.ALL))
                .willReturn(todayAllTraffic);

        given(trafficService.getTodayTrafficSummaries(TrafficType.THREAT))
                .willReturn(todayThreatTraffic);
    }

    @Test
    @DisplayName("주간 단위 요청")
    void getTotalTraffic_valid() throws Exception {
        mockMvc.perform(get("/api/traffics/{period}", PeriodType.WEEK.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists());
    }

    @Test
    @DisplayName("일간 단위 요청")
    void getTotalTraffic_day_valid() throws Exception {
        mockMvc.perform(get("/api/traffics/{period}", PeriodType.DAY.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists());
    }

    @Test
    @DisplayName("5분 단위 요청")
    void getTotalTraffic_five_minute_valid() throws Exception {
        mockMvc.perform(get("/api/traffics/{period}", PeriodType.FIVE_MINUTE.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists());
    }

    @Test
    @DisplayName("오늘의 모든 정상 트래픽 개수 요청")
    void getTodayNormalTraffic() throws Exception {
        mockMvc.perform(get("/api/traffics/summaries/{type}", TrafficType.ALL.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("count").exists())
        ;
    }

    @Test
    @DisplayName("오늘의 모든 비정상 트래픽 개수 요청")
    void getTodayAbnormalTraffic() throws Exception {
        mockMvc.perform(get("/api/traffics/summaries/{type}", TrafficType.THREAT.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("count").exists())
        ;
    }

    private static List<TotalTrafficResponse> generateTotalTrafficResponseByType(PeriodType periodType) {

        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        List<TotalTrafficResponse> list = new ArrayList<>();

        list.add(TotalTrafficResponse.builder()
                .id(1L)
                .count(30L)
                .timestamp(now)
                .build());

        long day = periodType.equals(PeriodType.WEEK) ? 7 : 1;

        if(periodType.equals(PeriodType.FIVE_MINUTE)) {
            for (long i = 2; i <= 8; i++) {
                list.add(TotalTrafficResponse.builder()
                        .id(i)
                        .count(20L + (7 + 21) * i)
                        .timestamp(now.minusMinutes(5))
                        .build());

                now = now.minusMinutes(5);
            }
        } else {
            for (long i = 2; i <= 8; i++) {
                list.add(TotalTrafficResponse.builder()
                        .id(i)
                        .count(20L + (i * 2 * 9 + 24 * 21) + (int) Math.pow(i, 3) * day * day * day)
                        .timestamp(now.minusDays(day))
                        .build());

                now = now.minusDays(day);
            }
        }

        return list;
    }
}