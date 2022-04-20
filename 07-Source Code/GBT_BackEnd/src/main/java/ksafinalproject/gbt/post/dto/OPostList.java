package ksafinalproject.gbt.post.dto;

import ksafinalproject.gbt.postImg.model.PostImg;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OPostList {

    private Long id;
    private String title;
    private String author;
    private String category;
    private LocalDateTime created;
    private Long likesCount;
    private Set<PostImg> postImg;
    private Long commentCount;

}
