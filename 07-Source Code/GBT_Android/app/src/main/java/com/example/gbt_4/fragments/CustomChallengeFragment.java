package com.example.gbt_4.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.gbt_4.CreateCustomChallenge;
import com.example.gbt_4.CustomChallengeDetail;
import com.example.gbt_4.CustomChallengeIng;
import com.example.gbt_4.OfficialChallengeDetail;
import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.adapter.CustomChallengeAdapter;
import com.example.gbt_4.dto.GetCustomChallengeDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomChallengeFragment extends Fragment {

    Button btn_create_custom_challenge;
    List<GetCustomChallengeDto> getCustomChallengeList;
    ListView listView;
    private CustomChallengeAdapter customChallengeAdapter;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom_challenge, container, false);
        listView = (ListView)v.findViewById(R.id.lv_custom_challenge);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<GetCustomChallengeDto>> call_allCustomChallenge = retrofitInterface.getCustomChallengeByUserId(1L);
        call_allCustomChallenge.enqueue(new Callback<List<GetCustomChallengeDto>>() {
            @Override
            public void onResponse(Call<List<GetCustomChallengeDto>> call, Response<List<GetCustomChallengeDto>> response) {
                if (response.isSuccessful()){
                    try {
                        getCustomChallengeList = response.body();
                        System.out.println("커스텀 챌린지 불러오기: 불러오기 완료");
                        customChallengeAdapter = new CustomChallengeAdapter(getContext(),getCustomChallengeList);
                        listView.setAdapter(customChallengeAdapter);
                    }catch (Exception e){
                        System.out.println("커스텀 챌린지 불러오기: 예외발생"+e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetCustomChallengeDto>> call, Throwable t) {
                System.out.println("커스텀 챌린지 불러오기: http통신 실패"+t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Long checkedId = getCustomChallengeList.get(position).getId();

                Intent intent = new Intent(getActivity(), CustomChallengeDetail.class);
                intent.putExtra("challengeId", checkedId);

                startActivity(intent);
            }
        });
//        Call<ResponseBody> call_allCustomChallenge = retrofitInterface.getCustomChallengeByUserId(1L);
//        call_allCustomChallenge.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()){
//                    try {
//                        ResponseBody result = response.body();
//                        System.out.println("커스텀 챌린지 불러오기: 불러오기 완료");
//                        System.out.println(">>>>>>>>>>>>>> result: "+result.toString());
//                        customChallengeAdapter = new CustomChallengeAdapter(getContext(),getCustomChallengeList);
//                        listView.setAdapter(customChallengeAdapter);
//                    }catch (Exception e){
//                        System.out.println("커스텀 챌린지 불러오기: 예외발생"+e.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                System.out.println("커스텀 챌린지 불러오기: http통신 실패"+t.toString());
//            }
//        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Long checkedId = getCustomChallengeList.get(position).getId();

                Intent intent = new Intent(getActivity(), CustomChallengeIng.class);
                intent.putExtra("challengeId", checkedId);

                startActivity(intent);
            }
        });




        btn_create_custom_challenge = (Button) v.findViewById(R.id.btn_create_custom_challenge);
        btn_create_custom_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateCustomChallenge.class);
                startActivity(intent);
            }
        });

        return v;
    }
}