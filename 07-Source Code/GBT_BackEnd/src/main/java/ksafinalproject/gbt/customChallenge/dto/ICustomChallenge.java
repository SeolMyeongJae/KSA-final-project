package ksafinalproject.gbt.customChallenge.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ICustomChallenge {

    private Long id;
    private Long creatorId;
    private String title;
    private String startDate;
    private String endDate;
    private String method;
    private Long frequency;
    private String bet;
    private String description;
    private String summary;
    private Long max;
}
