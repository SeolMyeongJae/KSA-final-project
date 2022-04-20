package ksafinalproject.gbt.customChallenge.dto;

import ksafinalproject.gbt.chat.model.Chat;
import ksafinalproject.gbt.customImg.model.CustomImg;
import ksafinalproject.gbt.invite.model.Invite;
import ksafinalproject.gbt.userCustom.model.UserCustom;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OCustomChallenge {

    private Long id;
    private Long creatorId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String method;
    private Long frequency;
    private String bet;
    private String summary;
    private String description;
    private Boolean isJoin;
    private Long current;
    private Long max;
    private List<Chat> chat;
    private List<CustomImg> customImg;
    private List<Invite> invite;
    private List<UserCustom> userCustom;
}
