package com.example.l7monitor.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnknownLogResponseData {
    private Long id;
    private String ip;
    private String data;
}
