package com.example.l7monitor.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityLevelResponse {
    private Integer level;
    private String description;
}
