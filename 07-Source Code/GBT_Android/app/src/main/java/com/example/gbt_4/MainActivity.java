package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gbt_4.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    Button btn_sign_up, btn_sign_in;

    Long userId;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //회원가입 버튼
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AgreeDialog.class);
                startActivity(intent);
            }
        });

        //로그인 버튼
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);
            }
        });


//        자동 로그인 기능
//        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE);
//        userId = sharedPreferences.getLong("userId",-1L);
//        System.out.println("11111111111111111111111111"+userId);
//
//        if (userId == -1){
//            Intent intent1 = new Intent(getApplicationContext(),InputInfo.class);
//            startActivity(intent1);
//        }else {
//            Intent intent2 = new Intent(getApplicationContext(),MainPage.class);
//            startActivity(intent2);
//        }
    }
}