package ksafinalproject.gbt.challengeImg.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OChallengeImg {
    private Long id;
    private String url;
    private Long challengeId;
}
