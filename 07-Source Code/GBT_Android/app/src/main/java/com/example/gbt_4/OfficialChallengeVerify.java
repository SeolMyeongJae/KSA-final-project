package com.example.gbt_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class OfficialChallengeVerify extends Activity {

    Button btn_official_challenge_verify_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verify);

        btn_official_challenge_verify_back = (Button) findViewById(R.id.btn_official_challenge_verify_back);

        btn_official_challenge_verify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}