package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetCustomChallengeDto {
    @SerializedName("creatorId")
    private Long creatorId;
    @SerializedName("description")
    private String description;
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
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("summary")
    private String summary;
    @SerializedName("title")
    private String title;
    @SerializedName("isJoin")
    private Boolean isJoin;
    @SerializedName("current")
    private Long current;
    @SerializedName("bet")
    private String bet;
}
