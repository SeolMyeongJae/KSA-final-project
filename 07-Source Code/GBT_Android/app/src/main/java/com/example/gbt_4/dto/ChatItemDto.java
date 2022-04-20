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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatItemDto {
    @SerializedName("userId")
    private Long userID;
    @SerializedName("message")
    private String message;
    @SerializedName("created")
    private LocalDateTime created;
    @SerializedName("customChallengeId")
    private Long customChallengeId;
    @SerializedName("userName")
    private String userName;
    @SerializedName("profileImg")
    private String profileImg;
//    private int chatType;
}
