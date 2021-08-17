package com.example.l7monitor.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Total {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String userid;
    private LocalDateTime timestamp;
    private String method;
    private String uri;
    private String protocol;
    private Integer resCode;
    private Integer resDataSize;
    private String referer;
    private String userAgent;
}
