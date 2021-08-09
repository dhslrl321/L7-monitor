package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.Normal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NormalRepository extends JpaRepository<Normal, Long> {
    long countByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
