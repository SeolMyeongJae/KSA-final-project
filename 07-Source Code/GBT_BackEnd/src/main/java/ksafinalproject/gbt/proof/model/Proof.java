package ksafinalproject.gbt.proof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ksafinalproject.gbt.challenge.model.Challenge;
import ksafinalproject.gbt.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime date;
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
