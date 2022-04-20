package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddSmokingDto {
    @SerializedName("userId")
    private Long userId;
    @SerializedName("provider")
    private String provider;

    public AddSmokingDto(Long userId, String provider) {
        this.userId = userId;
        this.provider = provider;
    }
}
