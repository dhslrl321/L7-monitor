package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.SecurityLevelResponse;
import com.example.l7monitor.domain.dto.TotalSummariesResponse;
import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.repository.AbnormalRepository;
import com.example.l7monitor.domain.repository.TotalRepository;
import com.example.l7monitor.domain.types.PeriodType;
import com.example.l7monitor.domain.types.SecurityLevelType;
import com.example.l7monitor.domain.types.TrafficType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrafficService {

    private TotalRepository totalRepository;
    private AbnormalRepository abnormalRepository;

    public TrafficService(TotalRepository totalRepository, AbnormalRepository abnormalRepository) {
        this.totalRepository = totalRepository;
        this.abnormalRepository = abnormalRepository;
    }

    /**
     * 시간 타입(5분, 하루, 일주일) 가 주어지면 8개의 size 를 가진 시간대 별의 트래픽 정보 List 를 반환한다.
     *
     * @param periodType : 5분, 하루, 일주일의 타입
     * @return 현재 시간으로 부터 특정 주기를 뺀 8개의 리스트를 반환한다.
     */
    public List<TotalTrafficResponse> getTrafficCountByPeriod(PeriodType periodType) {

        LocalDateTime from = null;
        LocalDateTime to = LocalDateTime.now();

        from = minusTimeByType(periodType, to);

        List<TotalTrafficResponse> response = new ArrayList<>();

        for (long i = 1; i <= 8; i++) {
            long count = totalRepository.countByTimestampBetween(from, to);

            response.add(TotalTrafficResponse.builder()
                    .id(i)
                    .timestamp(to)
                    .count(count)
                    .build());

            to = from;
            from = minusTimeByType(periodType, to);
        }

        return response;
    }

    /**
     * 현재를 기준으로 24시간 이내의 모든 비정상 요청의 개수를 출력한다.
     *
     * @param trafficType 트래픽 유형
     * @return 현재 시간으로부터 -24 시간의 모든 비정상 로그의 개수를 반환한다.
     */
    public TotalTrafficResponse getTodayTrafficSummaries(TrafficType trafficType) {
        LocalDateTime from = LocalDateTime.now().minusDays(1L);

        long count = 0;

        if(trafficType.equals(TrafficType.ALL)) {
            count = totalRepository.countByTimestampBetween(from, LocalDateTime.now());
        } else if (trafficType.equals(TrafficType.THREAT)) {
            count = abnormalRepository.countByTimestampBetween(from, LocalDateTime.now());
        }

        return TotalTrafficResponse.builder()
                .id(1L)
                .count(count)
                .timestamp(from)
                .build();
    }

    /**
     * 오늘의 보안 레벨을 계산하여 반환한다.
     * 계산식 : (비정상 요청 / 일반 요청)
     * @return
     */
    public SecurityLevelResponse getTodaySecurityLevel() {

        LocalDateTime from = LocalDateTime.now().minusDays(1L);

        long normalCount = totalRepository.countByTimestampBetween(from, LocalDateTime.now());
        long abnormalCount = abnormalRepository.countByTimestampBetween(from, LocalDateTime.now());

        SecurityLevelType level = null;

        double ratio = (double) abnormalCount / (double) normalCount;

        if(ratio <= 0.001) {
            level = SecurityLevelType.LEVEL1;
        } else if(0.001 < ratio && ratio <= 0.01) {
            level = SecurityLevelType.LEVEL2;
        } else if(0.01 < ratio && ratio <= 0.1) {
            level = SecurityLevelType.LEVEL3;
        } else if(0.1 < ratio) {
            level = SecurityLevelType.LEVEL4;
        }

        SecurityLevelResponse response = SecurityLevelResponse.builder()
                .level(level.getLevel())
                .description(level.getDescription())
                .build();

        response.changeRatioParsedString(ratio);

        return response;
    }

    /**
     * 오늘의 트래픽 요약을 한 번에 반환한다.
     * @return 오늘의 트래픽 요약
     */
    public TotalSummariesResponse getTodaySummaries() {
        LocalDateTime now = LocalDateTime.now();

        long allTraffic = totalRepository.countByTimestampBetween(now.minusDays(1L), now);

        long abnormalTraffic = abnormalRepository.countByTimestampBetween(now.minusDays(1L), now);

        TotalTrafficResponse totalTrafficResponse = TotalTrafficResponse.builder()
                .id(1L)
                .count(allTraffic)
                .timestamp(now.minusDays(1L))
                .build();

        TotalTrafficResponse abnormalTrafficResponse = TotalTrafficResponse.builder()
                .id(1L)
                .count(abnormalTraffic)
                .timestamp(now.minusDays(1L))
                .build();


        return TotalSummariesResponse.builder()
                .totalTraffic(totalTrafficResponse)
                .abnormalTraffic(abnormalTrafficResponse)
                .securityLevel(getTodaySecurityLevel())
                .build();
    }

    private static LocalDateTime minusTimeByType(PeriodType periodType, LocalDateTime time) {
        if (periodType.equals(PeriodType.WEEK)) {
            return time.minusDays(7L);
        } else if (periodType.equals(PeriodType.DAY)) {
            return time.minusDays(1L);
        } else if (periodType.equals(PeriodType.FIVE_MINUTE)) {
            return time.minusMinutes(5L);
        }

        return null;
    }
}
