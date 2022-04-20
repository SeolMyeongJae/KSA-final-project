package ksafinalproject.gbt.likes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ksafinalproject.gbt.post.model.Post;
import ksafinalproject.gbt.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @NotNull
    @JsonIgnore
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @NotNull
    private User user;

}
