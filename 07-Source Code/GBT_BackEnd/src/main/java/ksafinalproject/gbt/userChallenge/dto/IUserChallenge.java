package ksafinalproject.gbt.userChallenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class IUserChallenge {
    private Long id;
    private Long userId;
    private Long challengeId;
}
