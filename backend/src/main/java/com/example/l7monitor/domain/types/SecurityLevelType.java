package com.example.l7monitor.domain.types;

import lombok.Data;

public enum SecurityLevelType {
    LEVEL1(1, "정상 단계 (이상 트래픽 0.0001%) - 대부분의 트래픽이 정상이지만 이상 트래픽이 발생할 수 있습니다."),
    LEVEL2(2, "관심 단계 (이상 트래픽 0.001%) - 이상 트래픽이 탐지되고 있습니다."),
    LEVEL3(3, "주의 단계 (이상 트래픽 0.01%)- 이상 트래픽이 다수 탐지되고 있습니다."),
    LEVEL4(4, "심각 단계 (이상 트래픽 0.1%) - 이상 트래픽이 정상 수치를 넘어서고 대규모 피해로 확대될 가능성이 있습니다.");

    private Integer level;
    private String description;

    SecurityLevelType(Integer level, String description) {
        this.level = level;
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
