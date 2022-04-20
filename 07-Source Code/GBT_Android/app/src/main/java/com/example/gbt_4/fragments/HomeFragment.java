package com.example.gbt_4.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.CustomChallengeIng;
import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.OfficialChallengeVerify;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private SharedPreferences sharedPreferences;


    TextView tv_userName, tv_comment, tv_todayCount, tv_monthCount,btn_home_go_official_challenge_ing, btn_home_go_custom_challenge_ing;
    Button btn_plus, btn_home_certify;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        sharedPreferences = this.getActivity().getSharedPreferences("userId",MODE_PRIVATE);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //ID값 부여
        tv_userName = (TextView) v.findViewById(R.id.tv_home_name);
        tv_comment = (TextView) v.findViewById(R.id.tv_home_comment);
        tv_todayCount = (TextView) v.findViewById(R.id.tv_today_count);
//        tv_monthCount = (TextView) v.findViewById(R.id.tv_monthCount);
        btn_plus = (Button) v.findViewById(R.id.btn_plus);
        btn_home_certify = (Button) v.findViewById(R.id.btn_home_certify);
        btn_home_go_official_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_official_challenge_ing);
        btn_home_go_custom_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_custom_challenge_ing);

        // 유저 정보 가져오기 기능
        Call<GetUserDto> call_getUser = retrofitInterface.getByUserId(1L);
        call_getUser.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {

                        Long userId;

                        GetUserDto getUserDto1 = response.body();
                        tv_comment.setText(getUserDto1.getComment());
                        tv_userName.setText(getUserDto1.getUserName());

                        //내부저장
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("userId",getUserDto1.getUserId());
                        editor.commit();

                        userId = sharedPreferences.getLong("userId",-1);
                        System.out.println("22222222222222222222222222222222"+userId);

                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<GetUserDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //유저 1일 흡연량 가져오기 기능
        Call<GetSmokingDto> call_getSmokingDto = retrofitInterface.getTodayCount(1L);
        call_getSmokingDto.enqueue(new Callback<GetSmokingDto>() {
            @Override
            public void onResponse(Call<GetSmokingDto> call, Response<GetSmokingDto> response) {
                try {
                    GetSmokingDto getSmokingDto = response.body();
                    if (getSmokingDto.getCount()==null) {
                        tv_todayCount.setText("0");
                        getSmokingDto.setCount(0L);
                    }else if (getSmokingDto.getCount() != null){
                        tv_todayCount.setText(getSmokingDto.getCount().toString());
                    }
                }catch (Exception e){
                    System.out.println("예외발생!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //유저 1달 흡연량 가져오기
        Call<GetSmokingListDto> call_getSmokingListDto = retrofitInterface.getMonthCount(1L);
        call_getSmokingListDto.enqueue(new Callback<GetSmokingListDto>() {
            @Override
            public void onResponse(Call<GetSmokingListDto> call, Response<GetSmokingListDto> response) {
                try {
                    GetSmokingListDto getSmokingListDto = response.body();
                    if (getSmokingListDto.getTotal()==null){
                        tv_monthCount.setText("0");
                        getSmokingListDto.setTotal(0L);
                    }else if(getSmokingListDto.getTotal() != null){
                        tv_monthCount.setText(getSmokingListDto.getTotal().toString());
                    }

                }catch (Exception e){
                    System.out.println("예외발생!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingListDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //출석 인증 버튼
        btn_home_certify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), OfficialChallengeVerify.class);
                startActivity(intent1);
            }
        });

        //
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_plus:
                        AddSmokingDto addSmokingDto = new AddSmokingDto(1L, "설명재");
                        Call<Long> call_addSmoking = retrofitInterface.addSmoking(addSmokingDto);
                        call_addSmoking.enqueue(new Callback<Long>() {
                            @Override
                            public void onResponse(Call<Long> call, Response<Long> response) {
                                try {
                                    Toast.makeText(getActivity(), "담배 한 개비가 추가되었어요...", Toast.LENGTH_SHORT).show();
                                    tv_todayCount.setText(""+response.body());
                                    tv_monthCount.setText(""+(Integer.parseInt(tv_monthCount.getText().toString())+1L));

//                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                    ft.detach(HomeFragment.this).attach(HomeFragment.this).commit();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            @Override
                            public void onFailure(Call<Long> call, Throwable t) {
                                System.out.println("***********" + t.toString());
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });

        //공식 챌린지 바로가기
        btn_home_go_official_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OfficialChallengeIng.class);
                startActivity(intent);
            }
        });


        btn_home_go_custom_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustomChallengeFragment customChallengeFragment = new CustomChallengeFragment();
                fragmentTransaction.replace(R.id.frame_lo,customChallengeFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//        //번들로 Data 받아오기
//        Bundle bundle = getArguments();
//        GetUserDto getUserDto = (GetUserDto) bundle.getSerializable("user");
//        GetSmokingDto getSmokingDto = (GetSmokingDto) bundle.getSerializable("todayCount");
//        GetSmokingListDto getSmokingListDto = (GetSmokingListDto) bundle.getSerializable("monthCount");
        return v;
    }
}