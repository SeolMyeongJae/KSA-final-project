package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

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
public class AddCustomChallengeDto {
    @SerializedName("creatorId")
    private Long creatorId;
    @SerializedName("description")
    private String description;
    @SerializedName("img")
    private String img;
    @SerializedName("max")
    private Long max;
    @SerializedName("method")
    private Long method;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("summary")
    private String summary;
    @SerializedName("title")
    private String title;
    @SerializedName("bet")
    private String bet;

}
