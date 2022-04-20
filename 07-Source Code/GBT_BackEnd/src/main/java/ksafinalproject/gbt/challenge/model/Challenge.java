package ksafinalproject.gbt.challenge.model;

import javax.validation.constraints.NotNull;

import ksafinalproject.gbt.challengeImg.model.ChallengeImg;
import ksafinalproject.gbt.proof.model.Proof;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 255)
    private String title;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    @Column(length = 50)
    private String method;
    @NotNull
    private Long frequency;
    @NotNull
    @Column(length = 50)
    private String summary;
    @NotNull
    @Column(length = 255)
    private String description;
    @NotNull
    private Long max;
    private Long point;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "challenge")
    @ToString.Exclude
    private List<ChallengeImg> challengeImg;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "challenge")
    @ToString.Exclude
    private List<Proof> proof;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "challenge")
    @ToString.Exclude
    private List<UserChallenge> userChallenge;
}
