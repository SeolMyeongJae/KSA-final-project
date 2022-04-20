package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomChallengeSatus extends AppCompatActivity {

    Button btn_custom_challenge_status_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_custom_challenge_satus);

        btn_custom_challenge_status_back = (Button)findViewById(R.id.btn_custom_challenge_status_back);
        btn_custom_challenge_status_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                onBackPressed();
            }
        });
    }
}