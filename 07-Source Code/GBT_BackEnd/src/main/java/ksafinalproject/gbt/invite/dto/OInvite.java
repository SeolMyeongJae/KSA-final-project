package ksafinalproject.gbt.invite.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OInvite {
    private Long id;
    private String title;
    private String caller;
    private LocalDate date;
    private Long callerId;
    private Long userId;
    private Long customChallengeId;
}
