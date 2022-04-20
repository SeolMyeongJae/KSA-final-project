package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

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
public class GetUserDto implements Serializable {

    @SerializedName("id")
    private Long userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthYear")
    private Date birthYear;

    @SerializedName("smokingYear")
    private Long smokingYear;

    @SerializedName("comment")
    private String comment;

    @SerializedName("price")
    private Long price;

    @SerializedName("averageSmoking")
    private Long averageSmoking;

    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("popupImg")
    private String popupImg;

}
