package ksafinalproject.gbt.user.dto;

import ksafinalproject.gbt.chat.model.Chat;
import ksafinalproject.gbt.customChallenge.model.CustomChallenge;
import ksafinalproject.gbt.invite.model.Invite;
import ksafinalproject.gbt.likes.model.Likes;
import ksafinalproject.gbt.post.model.Post;
import ksafinalproject.gbt.proof.model.Proof;
import ksafinalproject.gbt.smoking.model.Smoking;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import ksafinalproject.gbt.userCustom.model.UserCustom;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OUser {
    private Long id;
    private String userName;
    private String gender;
    private LocalDate birthYear;
    private Long smokingYear;
    private String comment;
    private Long price;
    private Long averageSmoking;
    private Long ranking;
    private String profileImg;
    private String popupImg;
    private Long point;
    private Long badgeId;
    private Set<Post> post;
    private Set<CustomChallenge> customChallenge;
    private Set<Chat> chat;
    private Set<Proof> proof;
    private Set<Likes> likes;
    private Set<Invite> inviteCaller;
    private Set<Invite> inviteUser;
    private Set<UserChallenge> userChallenge;
    private Set<UserCustom> userCustom;
    private Set<Smoking> smoking;
}
