package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.NormalRepository;
import com.example.l7monitor.domain.type.PeriodType;
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

    private final NormalRepository normalRepository = mock(NormalRepository.class);

    @BeforeEach
    void init() {

        trafficService = new TrafficService(normalRepository);

        given(normalRepository.countByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(5L);
    }

    @ParameterizedTest
    @DisplayName("시간 타입(5분) 가 주어지면 8 개의 dto 반환 - 성공")
    @MethodSource("paramsForGetTrafficCountByPeriodWithValid")
    void getTrafficBetween_valid(PeriodType periodType) {
        List<TotalTrafficResponse> response = trafficService
                .getTrafficCountByPeriod(periodType);

        TotalTrafficResponse firstTotalResponseData = response.get(0);
        TotalTrafficResponse secondTotalResponseData = response.get(0);

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

    private static Stream<Arguments> paramsForGetTrafficCountByPeriodWithValid() {
        return Stream.of(
            Arguments.of(PeriodType.WEEK),
            Arguments.of(PeriodType.DAY),
            Arguments.of(PeriodType.FIVE_MINUTE)
        );
    }
}