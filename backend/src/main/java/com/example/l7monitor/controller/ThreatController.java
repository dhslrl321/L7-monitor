package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.ThreatLogCountResponse;
import com.example.l7monitor.service.ThreatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/threats", produces = "application/json")
public class ThreatController {

    private final ThreatService threatService;

    public ThreatController(ThreatService threatService) {
        this.threatService = threatService;
    }

    @GetMapping
    public ResponseEntity<ThreatLogCountResponse> getAllThreatLogs() {
        return ResponseEntity.ok(threatService.getAllThreatLogCount());
    }
}
