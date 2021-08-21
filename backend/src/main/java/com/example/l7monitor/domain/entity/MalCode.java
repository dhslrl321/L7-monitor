package com.example.l7monitor.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mal_code")
public class MalCode {
    @Id
    private Integer code;
    private String malName;
}
