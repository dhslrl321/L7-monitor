package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.entity.Normal;
import com.example.l7monitor.domain.repository.NormalRepository;
import com.example.l7monitor.domain.type.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
public class TrafficService {

    private NormalRepository normalRepository;

    public TrafficService(NormalRepository normalRepository) {
        this.normalRepository = normalRepository;
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
            long count = normalRepository.countByTimestampBetween(from, to);

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
