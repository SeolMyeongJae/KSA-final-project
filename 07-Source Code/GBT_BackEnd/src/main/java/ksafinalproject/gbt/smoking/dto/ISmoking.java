package ksafinalproject.gbt.smoking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ISmoking {

    private Long id;
    private Long count;
    private Long userId;
    private String provider;

}
