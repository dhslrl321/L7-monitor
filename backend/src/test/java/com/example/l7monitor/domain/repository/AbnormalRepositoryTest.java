package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Abnormal;
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
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class AbnormalRepositoryTest {

    private static final LocalDateTime[] times = {
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(6L)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(7L)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(8L)),
            LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(66L)),
            LocalDateTime.of(LocalDate.now().minusDays(7L), LocalTime.now())
    };

    @Autowired
    private AbnormalRepository abnormalRepository;

    @BeforeEach
    void setUp() {
        IntStream.range(0, 20).forEach(i -> generateAbnormalLogData(abnormalRepository, i));
    }

    @Test
    @DisplayName("특정 시간대 내에 존재하는 비정상 로그 개수 확인 - 성공")
    void countByTimestamp_valid() {

        LocalDateTime from = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(70));
        LocalDateTime to = LocalDateTime.now();

        long count = abnormalRepository.countByTimestampBetween(from, to);

        assertEquals(4, count);
    }

    private static void generateAbnormalLogData(JpaRepository abnormalRepository, int sequenceNumber) {

        if(sequenceNumber >= times.length) {
            sequenceNumber = times.length - 1;
        }

        String ip = "192.168.0." + sequenceNumber;

        Abnormal normal = Abnormal.builder()
                .ip(ip)
                .timestamp(times[sequenceNumber])
                .uri("/page?type=<script>alert(1)</script>")
                .malCode(200)
                .build();

        abnormalRepository.save(normal);
    }
}