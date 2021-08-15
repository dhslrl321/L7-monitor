package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.SecurityLevelResponse;
import com.example.l7monitor.domain.dto.ThreatLogCountResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.AbnormalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Service
@Transactional
public class ThreatService {
    private AbnormalRepository abnormalRepository;

    public ThreatService(AbnormalRepository abnormalRepository) {
        this.abnormalRepository = abnormalRepository;
    }

    /**
     * 14일 전의 모든 로그를 반환한다.
     * @return 각각 공격에 따른 로그 개수 (1: sqli, 2: rfi, 3: wshell, 4: xss)
     */
    public ThreatLogCountResponse getAllThreatLogCount() {

        TotalTrafficResponse[] threatResponses = new TotalTrafficResponse[4];

        IntStream.range(0, 4).forEach(i -> {
            long count = abnormalRepository.countByMalCodeCodeAndTimestampBetween(
                    i + 1, LocalDateTime.now().minusDays(14L), LocalDateTime.now());

            threatResponses[i] = TotalTrafficResponse.builder()
                    .id(1L)
                    .count(count)
                    .timestamp(LocalDateTime.now().minusDays(14L))
                    .build();
        });

        return ThreatLogCountResponse.builder()
                .sqli(threatResponses[0])
                .rfi(threatResponses[1])
                .wshell(threatResponses[2])
                .xss(threatResponses[3])
                .build();
    }
}
