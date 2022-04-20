package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CustomChallengeDetail extends AppCompatActivity {
    Button btn_custom_challenge_detail_back, btn_custom_challenge_detail_join;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_custom_challenge_detail);

        //dialog 세팅
        dialog = new Dialog(CustomChallengeDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_1);



        //뒤로가기 버튼
        btn_custom_challenge_detail_back = (Button) findViewById(R.id.btn_custom_challenge_detail_back);
        btn_custom_challenge_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //참여하기 버튼
        btn_custom_challenge_detail_join =(Button) findViewById(R.id.btn_custom_challenge_detail_join);
        btn_custom_challenge_detail_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }

            private void showDialog() {
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btn_yes = dialog.findViewById(R.id.btn_yes);
                Button btn_no = dialog.findViewById(R.id.btn_no);

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(),CustomChallengeIng.class);
//                        intent1.putExtra("checkedId",challengeId);
                        startActivity(intent1);
                        dialog.dismiss();
                        finish();
                    }
                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}