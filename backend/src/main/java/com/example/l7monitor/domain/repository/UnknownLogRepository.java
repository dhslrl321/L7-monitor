package com.example.l7monitor.domain.repository;

import com.example.l7monitor.domain.entity.UnknownLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnknownLogRepository extends JpaRepository<UnknownLog, Long> {
}
