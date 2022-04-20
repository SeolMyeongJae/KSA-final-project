package ksafinalproject.gbt.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class IChat {

    private Long id;
    private String message;
    private LocalDateTime created;
    private Long userId;
    private Long customChallengeId;
}
