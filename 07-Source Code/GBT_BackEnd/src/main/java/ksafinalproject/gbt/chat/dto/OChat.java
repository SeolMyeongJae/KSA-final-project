package ksafinalproject.gbt.chat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OChat {

    private Long id;
    private String message;
    private LocalDateTime created;
    private Long userId;
    private Long customChallengeId;
    private String userName;
    private String profileImg;
}
