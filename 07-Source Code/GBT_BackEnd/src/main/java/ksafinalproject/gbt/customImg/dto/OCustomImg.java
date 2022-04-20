package ksafinalproject.gbt.customImg.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OCustomImg {
    private Long id;
    private String url;
    private Long customChallengeId;
}
