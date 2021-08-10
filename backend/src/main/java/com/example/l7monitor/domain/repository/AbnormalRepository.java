package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Abnormal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AbnormalRepository extends JpaRepository<Abnormal, Long> {
    long countByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
