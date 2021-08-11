package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.SecurityLevelResponse;
import com.example.l7monitor.domain.dto.TotalSummariesResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.AbnormalRepository;
import com.example.l7monitor.domain.repository.TotalRepository;
import com.example.l7monitor.domain.types.PeriodType;
import com.example.l7monitor.domain.types.SecurityLevelType;
import com.example.l7monitor.domain.types.TrafficType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrafficServiceTest {

    private TrafficService trafficService;

    private final TotalRepository totalRepository = mock(TotalRepository.class);
    private final AbnormalRepository abnormalRepository = mock(AbnormalRepository.class);

    @BeforeEach
    void init() {

        trafficService = new TrafficService(totalRepository, abnormalRepository);

        given(totalRepository.countByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(12327L);
        
        given(abnormalRepository.countByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(417L);
    }

    @ParameterizedTest
    @DisplayName("시간 타입(5분) 가 주어지면 8 개의 dto 반환 - 성공")
    @MethodSource("paramsForGetTrafficCountByPeriodWithValid")
    void getTrafficBetween_valid(PeriodType periodType) {
        List<TotalTrafficResponse> response = trafficService
                .getTrafficCountByPeriod(periodType);

        TotalTrafficResponse firstTotalResponseData = response.get(0);

        assertAll(
                () -> assertEquals(8, response.size()),
                () -> assertEquals(1, firstTotalResponseData.getId()),
                () -> assertEquals(12327L, firstTotalResponseData.getCount()),
                () -> assertNotNull(firstTotalResponseData.getTimestamp())
        );
    }

    @Test
    @DisplayName("시간 타입(5분) 가 주어지면 8 개의 dto 반환 - 실패")
    void getTrafficBetween_invalid() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> trafficService.getTrafficCountByPeriod(null));

        assertEquals(NullPointerException.class, exception.getClass());
    }

    @ParameterizedTest
    @DisplayName("오늘 하루의 비정상 로그 개수 출력 - 성공")
    @MethodSource("paramsForGetTodayTrafficSummariesValid")
    void getTodayTrafficSummaries_valid(TrafficType trafficType) {
        TotalTrafficResponse response = trafficService
                .getTodayTrafficSummaries(trafficType);

        long count = trafficType.equals(TrafficType.ALL) ? 12327L : 417L;

        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals(count, response.getCount()),
                () -> assertNotNull(response.getTimestamp())
        );
    }

    @Test
    @DisplayName("오늘 하루의 보안 레벨 - 성공")
    void getTodaySecurityLevel() {
        SecurityLevelResponse response = trafficService.getTodaySecurityLevel();

        assertAll(
                () -> assertEquals(SecurityLevelType.LEVEL3.getLevel(), response.getLevel()),
                () -> assertEquals(SecurityLevelType.LEVEL3.getDescription(), response.getDescription()),
                () -> assertEquals("0.03383%", response.getRatio())
        );
    }

    @Test
    @DisplayName("오늘의 트랙픽 요약을 모두 반환한다 -성공")
    void getTodayTrafficsSummaries() {
        TotalSummariesResponse response = trafficService.getTodaySummaries();

        assertAll(
                () -> assertNotNull(response.getTotalTraffic()),
                () -> assertNotNull(response.getAbnormalTraffic()),
                () -> assertNotNull(response.getSecurityLevel())
        );
    }

    private static Stream<Arguments> paramsForGetTodayTrafficSummariesValid() {
        return Stream.of(
                Arguments.of(TrafficType.ALL),
                Arguments.of(TrafficType.THREAT)
        );
    }

    private static Stream<Arguments> paramsForGetTrafficCountByPeriodWithValid() {
        return Stream.of(
            Arguments.of(PeriodType.WEEK),
            Arguments.of(PeriodType.DAY),
            Arguments.of(PeriodType.FIVE_MINUTE)
        );
    }
}