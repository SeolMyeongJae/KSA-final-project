package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Invite extends AppCompatActivity {

    Button btn_invite_back, btn_invite_search_user,btn_invite_add;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_invite);


        //뒤로가기 버튼
        btn_invite_back = (Button) findViewById(R.id.btn_invite_back);
        btn_invite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //유저 찾기 버튼
        btn_invite_search_user = (Button) findViewById(R.id.btn_invite_search_user);
        btn_invite_search_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //체크된 유저 초대
        btn_invite_add = (Button) findViewById(R.id.btn_invite_add);
        btn_invite_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}