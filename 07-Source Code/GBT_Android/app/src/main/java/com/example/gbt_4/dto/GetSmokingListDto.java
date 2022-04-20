package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

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
public class GetSmokingListDto implements Serializable {
    @SerializedName("smokingList")
    List<GetSmokingDto> smokingDtoList;
    @SerializedName("total")
    Long total;
}
