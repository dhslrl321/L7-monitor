package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Abnormal;
import com.example.l7monitor.domain.entity.MalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AbnormalRepository extends JpaRepository<Abnormal, Long> {
    long countByTimestampBetween(LocalDateTime from, LocalDateTime to);

    long countByMalCodeCodeAndTimestampBetween(Integer malCode, LocalDateTime from, LocalDateTime to);
}
