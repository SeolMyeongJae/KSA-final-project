package com.example.gbt_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.dto.UserChallengeDto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficialChallengeDetail extends AppCompatActivity {

    TextView tv_official_challenge_detail_title,tv_official_challenge_detail_start,
            tv_official_challenge_detail_end, tv_official_challenge_detail_current, tv_official_challenge_detail_max,
            tv_official_challenge_detail_description, tv_official_challenge_detail_point;
    ImageView iv_official_challenge_detail_photo;
    Button btn_official_challenge_detail_join, btn_official_challenge_detail_back;
    Dialog dialog;

    private String photoURL;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

    UserChallengeDto userChallengeDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_official_challenge_detail);

        //dialog 세팅
        dialog = new Dialog(OfficialChallengeDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_1);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //Intent로 Data 받아오기
        Intent intent = getIntent();
        Long challengeId = intent.getLongExtra("challengeId", 0L);
        System.out.println("공식 챌린지 상세 페이지: 선택된 챌린지 Id는"+challengeId+"입니다.");


        tv_official_challenge_detail_title = (TextView)findViewById(R.id.tv_official_challenge_detail_title);
        tv_official_challenge_detail_current = (TextView)findViewById(R.id.tv_official_challenge_detail_current);
        tv_official_challenge_detail_max = (TextView)findViewById(R.id.tv_official_challenge_detail_max);
        tv_official_challenge_detail_start = (TextView)findViewById(R.id.tv_official_challenge_detail_start);
        tv_official_challenge_detail_end = (TextView)findViewById(R.id.tv_official_challenge_detail_end);
//        tv_official_challenge_method = (TextView)findViewById(R.id.tv_official_challenge_detail_method);
        tv_official_challenge_detail_description = (TextView)findViewById(R.id.tv_official_challenge_detail_description);
        iv_official_challenge_detail_photo = (ImageView)findViewById(R.id.iv_official_challenge_detail_photo);
        btn_official_challenge_detail_join = (Button)findViewById(R.id.btn_official_challenge_detail_join);
        btn_official_challenge_detail_back = (Button)findViewById(R.id.btn_official_challenge_detail_back);


        //해당 챌린지 상세정보 불러오기
        Call<GetOfficialChallengeDto> call_OfficialChallenge = retrofitInterface.getOfficialChallenge(challengeId);
        call_OfficialChallenge.enqueue(new Callback<GetOfficialChallengeDto>() {
            @Override
            public void onResponse(Call<GetOfficialChallengeDto> call, Response<GetOfficialChallengeDto> response) {
                if(response.isSuccessful()){
                    try {
                        GetOfficialChallengeDto getOfficialChallengeDto;
                        getOfficialChallengeDto = response.body();
                        tv_official_challenge_detail_title.setText(getOfficialChallengeDto.getTitle());
                        tv_official_challenge_detail_current.setText(getOfficialChallengeDto.getCurrent().toString());
                        tv_official_challenge_detail_max.setText(getOfficialChallengeDto.getMax().toString());
                        tv_official_challenge_detail_start.setText(getOfficialChallengeDto.getStartDate().toString());
                        tv_official_challenge_detail_end.setText(getOfficialChallengeDto.getEndDate().toString());
                        // TODO: 2022-04-08 endDate - startDate 날짜 계산하는거 구현하기
                        tv_official_challenge_detail_description.setText(getOfficialChallengeDto.getDescription());
                        photoURL = getOfficialChallengeDto.getImg();
                        // TODO: 2022-04-08 img URL로 찾기
                        Glide.with(OfficialChallengeDetail.this).load(photoURL).into(iv_official_challenge_detail_photo);
                        System.out.println("공식챌린지 상세페이지: 사진 URL은"+photoURL+"입니다.");

                    }catch (Exception e){
                        System.out.println("공식 챌린지 상세페이지:예외 오류!"+e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOfficialChallengeDto> call, Throwable t) {
                System.out.println("http통신 오류:"+ t.toString());
            }
        });

        btn_official_challenge_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //공식 챌린지 참여 버튼 기능
        btn_official_challenge_detail_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userChallengeDto = new UserChallengeDto(challengeId,1L);
                Call<Integer> call_participate = retrofitInterface.participateOfficialChallenge(userChallengeDto);
                call_participate.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()){
                            try {
                                GetOfficialChallengeDto getOfficialChallengeDto;
                                System.out.println("해당 공식 챌린지에 참여가 완료되었습니다.");
                                showDialog();
                                System.out.println(Integer.parseInt(response.body().toString()));
                            }catch (Exception e){
                                System.out.println("공식 챌린지 참여버튼 예외오류:"+e.getMessage());
                            }
                        }
                    }

                    //챌린지 도전 확인 팝업 다이얼로그
                    private void showDialog() {
                        dialog.show();
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        Button btn_yes = dialog.findViewById(R.id.btn_yes);
                        Button btn_no = dialog.findViewById(R.id.btn_no);

                        btn_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent1 = new Intent(getApplicationContext(),OfficialChallengeIng.class);
                                intent1.putExtra("checkedId",challengeId);
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

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println("공식 챌린지 참여버튼 통신오류"+t.toString());
                    }
                });
            }
        });

    }
}