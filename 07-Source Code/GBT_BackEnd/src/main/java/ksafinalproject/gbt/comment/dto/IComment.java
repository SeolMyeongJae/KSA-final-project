package ksafinalproject.gbt.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class IComment {

    private Long id;
    private String comment;
    private String author;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long postId;
}
