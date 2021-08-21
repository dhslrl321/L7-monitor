package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.UnknownLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class UnknownLogRepositoryTest {

    @Autowired
    private UnknownLogRepository unknownLogRepository;

    @BeforeEach
    void setUp() {
        IntStream.range(0, 20).forEach(i -> {
            UnknownLog unknownLog = UnknownLog.builder()
                    .id((long) i)
                    .ip("192.168.0.1")
                    .data("blah blah")
                    .build();

            unknownLogRepository.save(unknownLog);
        });
    }

    @Test
    @DisplayName("14일 이내의 모든 로그 확인하기")
    void getAllLogFrom14Days() {

        List<UnknownLog> allLog = unknownLogRepository.findAll();

        assertEquals(19, allLog.size());
    }
}