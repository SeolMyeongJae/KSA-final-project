package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GetOfficialChallengeDto {
    @SerializedName("current")
    private Long current;

    @SerializedName("description")
    private String description;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("frequency")
    private Long frequency;

    @SerializedName("id")
    private Long id;

    @SerializedName("img")
    private String img;

    @SerializedName("max")
    private Long max;

    @SerializedName("method")
    private String method;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("summary")
    private String summary;

    @SerializedName("title")
    private String title;

    @SerializedName("isJoin")
    private Boolean isJoin;

    @SerializedName("point")
    private Long point;
}
