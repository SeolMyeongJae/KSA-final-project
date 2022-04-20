package ksafinalproject.gbt.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class IPost {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String category;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long userId;
}
