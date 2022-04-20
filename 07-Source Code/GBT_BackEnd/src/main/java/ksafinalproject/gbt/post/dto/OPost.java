package ksafinalproject.gbt.post.dto;

import ksafinalproject.gbt.comment.model.Comment;
import ksafinalproject.gbt.likes.model.Likes;
import ksafinalproject.gbt.postImg.model.PostImg;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OPost {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String category;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long userId;
    private Long likesCount;
    private Set<PostImg> postImg;
    private Set<Comment> comment;
    private Set<Likes> likes;

}
