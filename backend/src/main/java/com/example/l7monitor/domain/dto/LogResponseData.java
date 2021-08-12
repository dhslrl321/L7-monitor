package com.example.l7monitor.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogResponseData {
    private String ip;
    private String userId;
    private LocalDateTime timestamp;
    private String method;
    private String uri;
    private String protocol;
    private Integer resCode;
    private Integer resDataSize;
    private String referer;
    private String userAgent;
}
