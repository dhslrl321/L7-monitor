package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Total;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class TotalRepositoryTest {
    @Autowired
    private TotalRepository totalRepository;

    private static final LocalDateTime[] times = {
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(4)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(6)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(7)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(8)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(11)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(12)),
    };

    @BeforeEach
    void init() {
        IntStream.range(0, 20).forEach(i -> generateNormalLogData(totalRepository, i));
    }

    @Test
    @DisplayName("get 테스트")
    void findAllNormalLog() {
        List<Total> totals = totalRepository.findAll();

        assertEquals(20, totals.size());
    }

    @Test
    @DisplayName("특정 시간대에 존재하는 모든 로그 개수를 출력 - 성공")
    void countByTimestampBetween_success() {

        LocalDateTime from = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(10)); // 현재 시간 -10 분
        LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(5)); // 현재 시간 -5 분

        long count = totalRepository.countByTimestampBetween(from, to);

        assertEquals(3, count);
    }

    @Test
    @DisplayName("24 시간 내의 로그를 모두 반환")
    void findAllByTimestampBetween_success() {
        List<Total> totalLogs = totalRepository.findAllByTimestampBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now());

        assertEquals(20, totalLogs.size());
    }

    private static void generateNormalLogData(JpaRepository normalRepository, int sequenceNumber) {

        if(sequenceNumber >= times.length) {
            sequenceNumber = times.length - 1;
        }

        String ip = "192.168.0." + sequenceNumber;

        Total total = Total.builder()
                .ip(ip)
                .timestamp(times[sequenceNumber])
                .method("GET")
                .uri("/images/pharming_img.png")
                .protocol("HTTP/1.1")
                .resCode(200)
                .resDataSize(53529)
                .referer("https://google.com")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko")
                .build();

        normalRepository.save(total);
    }
}