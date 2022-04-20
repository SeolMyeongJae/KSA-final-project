package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.dto.UserChallengeDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficialChallengeIng extends AppCompatActivity implements View.OnClickListener{

    Button btn_official_challenge_ing_status, btn_official_challenge_ing_verify, btn_official_challenge_ing_back;
    TextView tv_official_challenge_ing_title, tv_official_challenge_ing_start,tv_official_challenge_ing_end, tv_official_challenge_ing_current, tv_official_challenge_ing_max, tv_official_challenge_ing_method, tv_official_challenge_ing_description;
    ImageView iv_official_challenge_ing_photo;

    private String photoURL;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

//    UserChallengeDto userChallengeDto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_official_challenge_ing);

        btn_official_challenge_ing_status = (Button) findViewById(R.id.btn_official_challenge_ing_status);
        btn_official_challenge_ing_back = (Button) findViewById(R.id.btn_official_challenge_ing_back);

        tv_official_challenge_ing_title = (TextView)findViewById(R.id.tv_official_challenge_ing_title);
        tv_official_challenge_ing_current = (TextView)findViewById(R.id.tv_official_challenge_ing_current);
        tv_official_challenge_ing_max = (TextView)findViewById(R.id.tv_official_challenge_ing_max);
        tv_official_challenge_ing_start = (TextView)findViewById(R.id.tv_official_challenge_ing_start);
        tv_official_challenge_ing_end = (TextView)findViewById(R.id.tv_official_challenge_ing_end);
        tv_official_challenge_ing_description = (TextView)findViewById(R.id.tv_official_challenge_ing_description);
        iv_official_challenge_ing_photo = (ImageView)findViewById(R.id.iv_official_challenge_ing_photo);

        Intent intent1 = getIntent();
        Long challengeId = intent1.getLongExtra("checkedId",0L);
        System.out.println("진행중인 공식 챌린지: 선택된 챌린지 Id는"+challengeId+"입니다.");

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //해당 챌린지 상세정보 불러오기
        Call<GetOfficialChallengeDto> call_OfficialChallenge = retrofitInterface.getOfficialChallenge(challengeId);
        call_OfficialChallenge.enqueue(new Callback<GetOfficialChallengeDto>() {
            @Override
            public void onResponse(Call<GetOfficialChallengeDto> call, Response<GetOfficialChallengeDto> response) {
                if(response.isSuccessful()){
                    try {
                        GetOfficialChallengeDto getOfficialChallengeDto;
                        getOfficialChallengeDto = response.body();
                        tv_official_challenge_ing_title.setText(getOfficialChallengeDto.getTitle());
                        tv_official_challenge_ing_current.setText(getOfficialChallengeDto.getCurrent().toString());
                        tv_official_challenge_ing_max.setText(getOfficialChallengeDto.getMax().toString());
                        tv_official_challenge_ing_start.setText(getOfficialChallengeDto.getStartDate());
                        tv_official_challenge_ing_end.setText(getOfficialChallengeDto.getEndDate());
                        // TODO: 2022-04-08 endDate - startDate 날짜 계산하는거 구현하기
                        tv_official_challenge_ing_method.setText(getOfficialChallengeDto.getMethod());
                        tv_official_challenge_ing_description.setText(getOfficialChallengeDto.getDescription());
                        photoURL = getOfficialChallengeDto.getImg();
                        // TODO: 2022-04-08 img URL로 찾기
                        Glide.with(OfficialChallengeIng.this).load(photoURL).into(iv_official_challenge_ing_photo);
                        System.out.println("진행중인 공식챌린지 상세페이지: 사진 URL은"+photoURL+"입니다.");

                    }catch (Exception e){
                        System.out.println("진행중인 공식 챌린지 상세페이지:예외 오류!"+e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOfficialChallengeDto> call, Throwable t) {
                System.out.println("http통신 오류:"+ t.toString());
            }
        });


        btn_official_challenge_ing_status.setOnClickListener(this);
        btn_official_challenge_ing_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_official_challenge_ing_status:
                Intent intent2 = new Intent(getApplicationContext(),OfficialChallengeStatus.class);
                startActivity(intent2);
                break;
            case R.id.btn_official_challenge_ing_back:
                finish();
            default:
                break;
        }

    }
}