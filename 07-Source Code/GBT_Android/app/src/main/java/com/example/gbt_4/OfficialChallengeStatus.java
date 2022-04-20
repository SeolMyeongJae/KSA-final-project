package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

public class OfficialChallengeStatus extends Activity {

    FrameLayout fl_official_challenge_my_status, fl_official_challenge_whole_status;
    Button btn_official_challenge_status_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_official_challenge_status);


        fl_official_challenge_my_status = (FrameLayout) findViewById(R.id.fl_official_challenge_my_status);
        fl_official_challenge_whole_status = (FrameLayout) findViewById(R.id.fl_official_challenge_whole_status);

        btn_official_challenge_status_back = (Button) findViewById(R.id.btn_official_challenge_status_back);

        btn_official_challenge_status_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}