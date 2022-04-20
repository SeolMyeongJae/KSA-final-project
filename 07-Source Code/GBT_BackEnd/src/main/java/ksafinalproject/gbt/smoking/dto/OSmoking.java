package ksafinalproject.gbt.smoking.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OSmoking {
    private Long id;
    private Long count;
    private LocalDate date;
    private Long userId;
    private String provider;
}
