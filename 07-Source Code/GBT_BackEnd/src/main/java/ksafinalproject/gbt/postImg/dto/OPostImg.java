package ksafinalproject.gbt.postImg.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OPostImg {
    private Long id;
    private String url;
    private Long postId;
}
