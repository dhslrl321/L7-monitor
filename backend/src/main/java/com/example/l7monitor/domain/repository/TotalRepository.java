package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Total;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TotalRepository extends JpaRepository<Total, Long> {
    long countByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
