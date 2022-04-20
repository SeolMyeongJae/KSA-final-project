package ksafinalproject.gbt.customImg.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ICustomImg {

    private Long id;
    private String url;
    private Long customChallengeId;
}
