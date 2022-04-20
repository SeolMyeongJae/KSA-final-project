package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gbt_4.adapter.ChatAdapter;
import com.example.gbt_4.dto.ChatItemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {
    Button btn_chat_room_back, btn_chat_room_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_chat_room_back = (Button)findViewById(R.id.btn_chat_room_back);
        btn_chat_room_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_chat_room_submit = (Button)findViewById(R.id.btn_chat_room_submit);

//        //더미데이터 리스트
//        ArrayList<String> list = new ArrayList<String>();
//        for(int i=0;i<=10;i++){
//            list.add(String.format("Text %d",i));
//        }


        ChatItemDto chat2 = new ChatItemDto(2L,"채팅2", LocalDateTime.now(),1L,"황정민",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fweb.dominos.co.kr%2Fgoods%2Fdetail%3Fdsp_ctgr%3DC0101%26code_01%3DRPZ007SL%26dough_gb%3D203&psig=AOvVaw2LUpZeF0DTlNYg7oeS4Yic&ust=1650421684713000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCOCJ2YSKn_cCFQAAAAAdAAAAABAD");
        ArrayList<ChatItemDto> chatDtoList = new ArrayList<>();
        ChatItemDto chat1 = new ChatItemDto(1L,"채팅1", LocalDateTime.now(),1L,"설명재",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpelicana.co.kr%2Fmenu%2Fbest_menu.html&psig=AOvVaw3gegQ3_zLAUKBvvDlbTB1D&ust=1650421650308000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNidovSJn_cCFQAAAAAdAAAAABAD");
        chatDtoList.add(chat1);
        chatDtoList.add(chat2);
        chatDtoList.add(chat2);
        chatDtoList.add(chat2);
        chatDtoList.add(chat1);
        chatDtoList.add(chat2);
        chatDtoList.add(chat2);
        chatDtoList.add(chat1);
        chatDtoList.add(chat1);
        chatDtoList.add(chat1);
        chatDtoList.add(chat2);
//        List;
        ArrayList<ChatItem> chatList = new ArrayList<>();

        for(ChatItemDto chat : chatDtoList) {
            int chatType;
            if(chat.getUserID()==1L){
                chatType=1;
            }else {
                chatType=2;
            }
            ChatItem chatItem = new ChatItem(chat, chatType);
            chatList.add(chatItem);
        }



        RecyclerView recyclerView = findViewById(R.id.rv_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatAdapter chatAdapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(chatAdapter);

    }
}