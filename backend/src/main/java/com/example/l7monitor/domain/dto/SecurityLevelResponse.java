package com.example.l7monitor.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityLevelResponse {
    private Integer level;
    private String description;
    private String ratio;

    /**
     * double type 의 비율을 받아 소수점 5번째 자리까지 자른다.
     *
     * 정상 (12327) : 이상 (11) = 0.00089%
     * 정상 (12327) : 이상 (41) = 0.00333%
     * 정상 (12327) : 이상 (231) = 0.01874%
     * 정상 (12327) : 이상 (4121) = 0.33431%
     *
     * @param ratio : double type 의 (정상 로그 / 이상 로그)
     */
    public void changeRatioParsedString(double ratio) {
        this.ratio = String.format("%.5f", ratio) + "%";
    }
}
