package ksafinalproject.gbt.smoking.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalSmokingAndDays {
    private Long total;
    private Long smokingDays;
    private Long period;
}
