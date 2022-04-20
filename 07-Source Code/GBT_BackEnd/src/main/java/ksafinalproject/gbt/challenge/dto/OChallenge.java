package ksafinalproject.gbt.challenge.dto;

import ksafinalproject.gbt.challengeImg.model.ChallengeImg;
import ksafinalproject.gbt.proof.model.Proof;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OChallenge {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String method;
    private Long frequency;
    private String summary;
    private String description;
    private Boolean isJoin;
    private Long current;
    private Long max;
    private Long point;
    private List<ChallengeImg> challengeImg;
    private List<Proof> proof;
    private List<UserChallenge> userChallenge;

}
