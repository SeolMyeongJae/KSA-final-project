package ksafinalproject.gbt.proof.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OProof {
    private Long id;
    private String content;
    private LocalDateTime date;
    private Long userId;
    private Long challengeId;
}
