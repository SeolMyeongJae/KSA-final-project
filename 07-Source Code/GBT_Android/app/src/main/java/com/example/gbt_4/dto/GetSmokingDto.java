package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
public class GetSmokingDto implements Serializable {
    @SerializedName("userId")
    private Long userId;
    @SerializedName("count")
    private Long count;
    @SerializedName("provider")
    private String provider;
}
