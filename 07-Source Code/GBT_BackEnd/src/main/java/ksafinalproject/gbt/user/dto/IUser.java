package ksafinalproject.gbt.user.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class IUser {

    private Long id;
    private String userName;
    private String gender;
    private int birthYear;
    private Long smokingYear;
    private String comment;
    private Long price;
    private Long averageSmoking;
    private Long ranking;
    private String profileImg;
    private String popupImg;
    private Long point;
    private Long badgeId;

}
