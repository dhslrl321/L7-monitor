package com.example.l7monitor.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MalCode {
    @Id
    private Integer code;
    private String malName;
}
