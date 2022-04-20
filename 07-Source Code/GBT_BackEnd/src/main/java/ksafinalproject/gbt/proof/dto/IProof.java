package ksafinalproject.gbt.proof.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class IProof {
    private Long id;
    private String content;
    private LocalDateTime date;
    private Long userId;
    private Long challengeId;
}
