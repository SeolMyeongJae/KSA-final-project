package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.dto.AddUserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InputInfo extends AppCompatActivity{
    private final String TAG = "InputInfo_Log:";
    private final String URL = "http://54.219.40.82/api/";


//    버튼 정의
    private Button btn_submit, btn_select_year;
//    입력창 정의
    private EditText et_nickname,et_gender, et_smoking_amount, et_smoking_year,et_price, et_comment;
//    텍스트뷰 정의
    private TextView tv_birthYear;
//    문자열 정의
    private String nickname, gender, comment, profileImg, popupImg;
//    숫자열 정의
    private Long birthYear, smokingYear, price, smokingAmount, ranking, point, badId;

//    레트로핏 객체 생성
    private Retrofit retrofit;
//    인터페이스 객체 생성
    private RetrofitInterface retrofitInterface;

//    날짜선택창 객체 생성
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Log.d("YearMonthPickerTest", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
            String yearStr = Integer.toString(year);
            tv_birthYear.setText(yearStr);
            birthYear = Long.parseLong(tv_birthYear.getText().toString());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_input_info);

//        버튼 id값 부여
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_select_year = findViewById(R.id.btn_select_year);
//        입력창 id값 부여
        et_nickname = (EditText)findViewById(R.id.et_nickname);
        et_gender = (EditText)findViewById(R.id.et_gender);
        et_price = (EditText)findViewById(R.id.et_price);
        et_comment = (EditText)findViewById(R.id.et_comment);
        et_smoking_amount = (EditText)findViewById(R.id.et_smoking_amount);
        et_smoking_year = (EditText)findViewById(R.id.et_smoking_year);
//        텍스트뷰 id값 부여
        tv_birthYear = (TextView) findViewById(R.id.tv_birthYear);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        //날짜 선택 버튼 기능
        btn_select_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectYear selectYear = new SelectYear();
                selectYear.setListener(d);
                selectYear.show(getSupportFragmentManager(),"출생년도 선택");
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                데이터 입력(완료) -> 앞으로는 임시로 더미데이터를 사용
//                nickname = et_nickname.getText().toString();
//                gender = et_gender.getText().toString();
//                price = Long.parseLong(et_price.getText().toString());
//                comment = et_comment.getText().toString();
//                smokingYear = Long.parseLong(et_smoking_year.getText().toString());
//                smokingAmount = Long.parseLong(et_smoking_amount.getText().toString());
//                profileImg = "더미데이터";
//                popupImg = "더미데이터";
//                badId = 0L;
//                point = 0L;
//                ranking = 0L;

                nickname = "설명재";
                gender = "남자";
                price =4500L;
                comment = "끊어봅시다!";
                smokingYear = 8L;
                smokingAmount = 20L;
                profileImg = "더미데이터";
                popupImg = "더미데이터";
                badId = 0L;
                point = 0L;
                ranking = 0L;



                //              addUserDto 사용
                AddUserDto addUserDto = new AddUserDto(nickname, gender, birthYear, smokingYear,comment,price,smokingAmount,ranking, profileImg,popupImg, point, badId);
                System.out.println(addUserDto);

                Call<Integer> call_post = retrofitInterface.addUser(addUserDto);
                call_post.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            try {
                                int result = Integer.parseInt(response.body().toString());
                            }catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);


                finish();
            }
        });
    }
}