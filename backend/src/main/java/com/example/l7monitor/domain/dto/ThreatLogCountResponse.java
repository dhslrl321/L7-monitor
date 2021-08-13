package com.example.l7monitor.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreatLogCountResponse {
    private TotalTrafficResponse sqli;
    private TotalTrafficResponse rfi;
    private TotalTrafficResponse wshell;
    private TotalTrafficResponse xss;
}
