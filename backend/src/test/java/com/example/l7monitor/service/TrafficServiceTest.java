package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.SecurityLevelResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.AbnormalRepository;
import com.example.l7monitor.domain.repository.TotalRepository;
import com.example.l7monitor.domain.types.PeriodType;
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
                .willReturn(5L);
        
        given(abnormalRepository.countByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(7L);
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
                () -> assertEquals(5, firstTotalResponseData.getCount()),
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

        long count = trafficType.equals(TrafficType.ALL) ? 5 : 7;

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
                () -> assertEquals(3, response.getLevel()),
                () -> assertEquals("관심 단계", response.getLevel())
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