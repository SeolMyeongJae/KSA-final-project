package ksafinalproject.gbt.customChallenge.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ksafinalproject.gbt.chat.model.Chat;
import ksafinalproject.gbt.customImg.model.CustomImg;
import ksafinalproject.gbt.invite.model.Invite;
import ksafinalproject.gbt.user.model.User;
import ksafinalproject.gbt.userCustom.model.UserCustom;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotNull
    @JsonIgnore
    private User creator;
    @NotNull
    @Column(length = 255)
    private String title;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @Column(length = 50)
    private String method;
    private Long frequency;
    @Column(length = 100)
    private String bet;
    @NotNull
    @Column(length = 50)
    private String summary;
    @NotNull
    @Column(length = 255)
    private String description;
    private Long max;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "customChallenge")
    @ToString.Exclude
    private List<Chat> chat;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "customChallenge")
    @ToString.Exclude
    private List<CustomImg> customImg;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "customChallenge")
    @ToString.Exclude
    private List<Invite> invite;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "customChallenge")
    @ToString.Exclude
    private List<UserCustom> userCustom;
}
