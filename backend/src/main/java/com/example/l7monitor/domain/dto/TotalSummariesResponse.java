package com.example.l7monitor.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalSummariesResponse {
    private TotalTrafficResponse totalTraffic;
    private TotalTrafficResponse abnormalTraffic;
    private SecurityLevelResponse securityLevel;
}
