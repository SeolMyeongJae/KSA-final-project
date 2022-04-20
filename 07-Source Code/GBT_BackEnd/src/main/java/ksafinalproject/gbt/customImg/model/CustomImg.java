package ksafinalproject.gbt.customImg.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ksafinalproject.gbt.customChallenge.model.CustomChallenge;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 255)
    private String url;
    @ManyToOne
    @JoinColumn(name = "custom_challenge_id")
    @NotNull
    @JsonIgnore
    private CustomChallenge customChallenge;
}
