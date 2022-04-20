package ksafinalproject.gbt.userChallenge.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OUserChallenge {
    private Long id;
    private Long userId;
    private Long challengeId;
}
