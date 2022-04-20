package ksafinalproject.gbt.userChallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ksafinalproject.gbt.challenge.model.Challenge;
import ksafinalproject.gbt.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "challenge_id")
    @NotNull
    @JsonIgnore
    private Challenge challenge;

}
