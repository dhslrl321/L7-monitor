package com.example.l7monitor.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalTrafficResponse {
    private Long id;
    private Long count;
    private LocalDateTime timestamp;
}
