package com.example.l7monitor.controller;

import com.example.l7monitor.domain.dto.TotalTrafficResponse;
import com.example.l7monitor.domain.type.PeriodType;
import com.example.l7monitor.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/traffics", produces = "application/json")
public class TrafficController {

    private final TrafficService trafficService;

    public TrafficController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @GetMapping("/{period}")
    public ResponseEntity<List<TotalTrafficResponse>> getTotalTrafficByPeriod(@PathVariable String period) {
        return ResponseEntity.ok(trafficService.getTrafficCountByPeriod(PeriodType.valueOf(period.toUpperCase(Locale.ROOT))));
    }
}
