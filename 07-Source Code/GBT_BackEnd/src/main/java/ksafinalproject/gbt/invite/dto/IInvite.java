package ksafinalproject.gbt.invite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class IInvite {

    private Long id;
    private String title;
    private String caller;
    private LocalDate date;
    private Long callerId;
    private Long userId;
    private Long customChallengeId;
}
