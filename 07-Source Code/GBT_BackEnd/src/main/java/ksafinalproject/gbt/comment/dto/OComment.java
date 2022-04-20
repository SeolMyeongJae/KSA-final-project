package ksafinalproject.gbt.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OComment {
    private Long id;
    private String comment;
    private String author;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long postId;
}
