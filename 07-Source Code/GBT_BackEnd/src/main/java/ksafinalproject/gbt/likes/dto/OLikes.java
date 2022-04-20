package ksafinalproject.gbt.likes.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OLikes {
    private Long id;
    private Long postId;
    private Long userid;
}
