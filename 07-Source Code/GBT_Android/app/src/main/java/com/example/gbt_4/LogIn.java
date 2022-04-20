package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    Button btn_google, btn_kakao, btn_naver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_log_in);


        btn_google = (Button) findViewById(R.id.btn_google);
        btn_kakao = (Button) findViewById(R.id.btn_kakao);
        btn_naver = (Button) findViewById(R.id.btn_naver);


        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InputInfo.class);
                startActivity(intent);
            }
        });
        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InputInfo.class);
                startActivity(intent);
            }
        });
        btn_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InputInfo.class);
                startActivity(intent);
            }
        });
    }
}