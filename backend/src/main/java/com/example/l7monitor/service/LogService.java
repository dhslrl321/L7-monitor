package com.example.l7monitor.service;

import com.example.l7monitor.domain.dto.LogResponseData;
import com.example.l7monitor.domain.dto.UnknownLogResponseData;
import com.example.l7monitor.domain.entity.Total;
import com.example.l7monitor.domain.entity.UnknownLog;
import com.example.l7monitor.domain.repository.TotalRepository;
import com.example.l7monitor.domain.repository.UnknownLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogService {

    private final TotalRepository totalRepository;
    private final UnknownLogRepository unknownLogRepository;
    private final ModelMapper modelMapper;

    public LogService(TotalRepository totalRepository,
                      UnknownLogRepository unknownLogRepository,
                      ModelMapper modelMapper) {
        this.totalRepository = totalRepository;
        this.unknownLogRepository = unknownLogRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * 24 시간 발생한 로그를 반환한다.
     * @return Log Dto
     */
    public List<LogResponseData> getAllLog() {
        List<Total> totalLogs = totalRepository.findTop100ByTimestampBetween(
                LocalDateTime.now().minusDays(1L), LocalDateTime.now());

        return totalLogs.stream()
                .map(log -> modelMapper.map(log, LogResponseData.class))
                .collect(Collectors.toList());
    }

    /**
     * 식별이 불가능한 모든 로그를 반환한다.
     *
     * @return UnknownLog Dto
     */
    public List<UnknownLogResponseData> getAllUnknownLog() {
        List<UnknownLog> unknownLogs = unknownLogRepository.findAll();

        return unknownLogs.stream()
                .map(log -> modelMapper.map(log, UnknownLogResponseData.class))
                .collect(Collectors.toList());
    }
}
