package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gbt_4.dto.AddCustomChallengeDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class CreateCustomChallenge extends AppCompatActivity {
    private final String URL = "http://54.219.40.82/api/";

    Button btn_create_custom_challenge_generate,btn_yes_create_challenge,btn_no_create_challenge,btn_create_custom_challenge_back;
    Dialog dialog;
    EditText et_create_custom_challenge_title,et_create_custom_challenge_bet,et_create_custom_challenge_max,
            et_create_custom_challenge_summary,et_create_custom_challenge_description;
    Spinner spinner;

    TextView tv_create_custom_challenge_start_date,tv_create_custom_challenge_end_date,tv_create_custom_challenge_start_time,tv_create_custom_challenge_end_time,tv_create_custom_challenge_method;
    String title, bet, summary, description,start_date_string, end_date_string,start_time_string,end_time_string, start_string,end_string;
    Long max;
    Long method = 1L;

    String photoURL;


    //    레트로핏 객체 생성
    private Retrofit retrofit;
    //    인터페이스 객체 생성
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_custom_challenge);

        //id값 부여
        tv_create_custom_challenge_start_date = (TextView) findViewById(R.id.tv_create_custom_challenge_start_date);
        tv_create_custom_challenge_end_date = (TextView) findViewById(R.id.tv_create_custom_challenge_end_date);
        tv_create_custom_challenge_start_time = (TextView)findViewById(R.id.tv_create_custom_challenge_start_time);
        tv_create_custom_challenge_end_time = (TextView)findViewById(R.id.tv_create_custom_challenge_end_time);
//        tv_create_custom_challenge_method = (TextView)findViewById(R.id.tv_create_custom_challenge_method);

        et_create_custom_challenge_title = (EditText)findViewById(R.id.et_create_custom_challenge_title);
        et_create_custom_challenge_bet = (EditText)findViewById(R.id.et_create_custom_challenge_bet);
        et_create_custom_challenge_max = (EditText)findViewById(R.id.et_create_custom_challenge_max);
        et_create_custom_challenge_summary = (EditText)findViewById(R.id.et_create_custom_challenge_summary);
        et_create_custom_challenge_description = (EditText)findViewById(R.id.et_create_custom_challenge_description);

        //뒤로가기 버튼
        btn_create_custom_challenge_back = (Button)findViewById(R.id.btn_create_custom_challenge_back);
        btn_create_custom_challenge_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinner = (Spinner)findViewById(R.id.sp_create_custom_challenge_method);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position==0){
                    method = 1L;
                }else if(position==1) {
                    method = 2L;
                }
                System.out.println(method);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //dialog 세팅
        dialog = new Dialog(CreateCustomChallenge.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_2);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //챌린지 기간 선택 기능
        tv_create_custom_challenge_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartDatePicker(view);
            }
        });

        tv_create_custom_challenge_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePicker(view);
            }
        });

        tv_create_custom_challenge_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndDatePicker(view);
            }
        });

        tv_create_custom_challenge_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePicker(view);
            }
        });

        //'생성'버튼 기능
        btn_create_custom_challenge_generate = (Button) findViewById(R.id.btn_create_custom_challenge_generate);
        btn_create_custom_challenge_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }

            //생성확인 dialog 설정
            private void showDialog() {
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btn_yes_create_challenge = (Button) dialog.findViewById(R.id.btn_yes_create_challenge);
                btn_no_create_challenge = (Button) dialog.findViewById(R.id.btn_no_create_challenge);

                //'네'버튼 기능
                btn_yes_create_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title = et_create_custom_challenge_title.getText().toString();
                        bet  = et_create_custom_challenge_bet.getText().toString();
//                        method = et_create_custom_challenge_method.getText().toString();
                        summary = et_create_custom_challenge_summary.getText().toString();
                        description = et_create_custom_challenge_description.getText().toString();

                        max = Long.parseLong(""+et_create_custom_challenge_max.getText());
                        start_string = start_date_string+"-"+start_time_string;
                        end_string = end_date_string+"-"+end_time_string;

                        System.out.println("start_string: "+start_string);
                        System.out.println("end_string: "+end_string);

                        photoURL = "임시 스트링";

                        AddCustomChallengeDto addCustomChallengeDto = new AddCustomChallengeDto(1L,description,photoURL,max,method,start_string,end_string,summary,title,bet);
                        System.out.println(addCustomChallengeDto.toString());


                        //입력된 data로 restAPI통신
                        Call<Integer> call_post = retrofitInterface.addCustomChallenge(addCustomChallengeDto);
                        call_post.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if(response.isSuccessful()){
                                    try {
                                        System.out.println("커스텀 챌린지가 성공적으로 생성 되었습니다!");
                                        System.out.println(response.body().toString());
                                    }catch (NumberFormatException n){
                                        n.printStackTrace();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                System.out.println("커스텀 챌린지 생성 http통신 오류: "+t.getMessage());
                            }
                        });
                        Intent intent1 = new Intent(getApplicationContext(),CustomChallengeIng.class);
                        startActivity(intent1);
                        dialog.dismiss();
                        finish();
                    }
                });

                //'아니오'버튼 기능
                btn_no_create_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
    });
}

    //StartDatePicker 호출
    public void showStartDatePicker(View view){
    DialogFragment dialogFragment = new SelectStartDay();
    dialogFragment.show(getSupportFragmentManager(),"startDatePicker");
    }
    //EndDatePicker 호츌
    public void showEndDatePicker(View view){
    DialogFragment dialogFragment = new SelectEndDate();
    dialogFragment.show(getSupportFragmentManager(),"endDatePicker");
    }
    //StartTimePicker 호출
    public void showStartTimePicker(View view){
        DialogFragment dialogFragment = new SelectStartTime();
        dialogFragment.show(getSupportFragmentManager(),"startTimePicker");
    }
    //EndTimePicker 호출
    public void showEndTimePicker(View view){
        DialogFragment dialogFragment = new SelectEndTime();
        dialogFragment.show(getSupportFragmentManager(),"endTimePicker");
    }

    public void processStartDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        start_date_string = year_string+"-"+month_string+"-"+day_string;
        tv_create_custom_challenge_start_date.setText(year_string+"년 "+month_string+"월 "+day_string+"일");
    }

    public void processEndDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        end_date_string = year_string+"-"+month_string+"-"+day_string;
        tv_create_custom_challenge_end_date.setText(year_string+"년 "+month_string+"월 "+day_string+"일");
    }

    public void processStartTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        start_time_string = hour_string+"-"+minute_string;
        tv_create_custom_challenge_start_time.setText(hour+"시 "+minute+"분 부터");
    }

    public void processEndTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        end_time_string = hour_string+"-"+minute_string;
        tv_create_custom_challenge_end_time.setText(hour+"시 "+minute+"분 까지");

    }
}

