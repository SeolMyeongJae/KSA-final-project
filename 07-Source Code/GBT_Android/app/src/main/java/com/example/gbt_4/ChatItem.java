package com.example.gbt_4;

import com.example.gbt_4.dto.ChatItemDto;
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
public class ChatItem {
    ChatItemDto chatItemDto;
    private int chatType;
}
