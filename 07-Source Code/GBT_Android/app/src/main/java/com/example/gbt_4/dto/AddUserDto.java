package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
    @SerializedName("userName")
    private String userName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthYear")
    private Long birthYear;

    @SerializedName("smokingYear")
    private Long smokingYear;

    @SerializedName("comment")
    private String comment;

    @SerializedName("price")
    private Long price;

    @SerializedName("averageSmoking")
    private Long averageSmoking;

    @SerializedName("ranking")
    private Long ranking;

    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("popupImg")
    private String popupImg;

    @SerializedName("point")
    private Long point;

    @SerializedName("badgeId")
    private Long badgeId;

}
