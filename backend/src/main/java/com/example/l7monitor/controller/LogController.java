package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.LogResponseData;
import com.example.l7monitor.domain.dto.UnknownLogResponseData;
import com.example.l7monitor.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/logs", produces = "application/json")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LogResponseData>> getAllLogs() {
        return ResponseEntity.ok().body(logService.getAllLog());
    }

    @GetMapping("/unknown")
    public ResponseEntity<List<UnknownLogResponseData>> getAllUnknownLogs() {
        return ResponseEntity.ok().body(logService.getAllUnknownLog());
    }

}
