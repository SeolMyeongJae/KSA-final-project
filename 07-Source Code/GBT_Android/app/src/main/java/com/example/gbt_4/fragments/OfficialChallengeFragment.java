package com.example.gbt_4.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gbt_4.OfficialChallengeDetail;
import com.example.gbt_4.adapter.OfficialChallengeAdapter;
import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.dto.GetOfficialChallengeDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficialChallengeFragment extends Fragment{

    List<GetOfficialChallengeDto> getOfficialChallengeList;
    ListView listView;
    private static OfficialChallengeAdapter officialChallengeAdapter;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_official_challenge, container, false);
        listView = (ListView) v.findViewById(R.id.lv_official_challenge);


        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<GetOfficialChallengeDto>> call_allOfficialChallenge = retrofitInterface.getAllOfficialChallenge(1L);
        call_allOfficialChallenge.enqueue((new Callback<List<GetOfficialChallengeDto>>() {
            @Override
            public void onResponse(Call<List<GetOfficialChallengeDto>> call, Response<List<GetOfficialChallengeDto>> response) {
                if(response.isSuccessful()){
                    try {
                        getOfficialChallengeList = response.body();
                        System.out.println("공식 챌린지 정보: 불러오기 완료");
                        officialChallengeAdapter = new OfficialChallengeAdapter(getContext(), getOfficialChallengeList);

                        listView.setAdapter(officialChallengeAdapter);
                    }catch (Exception e){
                        System.out.println("공식 챌린지 정보: 불러오기 실패");
                    }
                }else {
                    System.out.println("공식 챌린지 정보:기타 예외 발생");
                }
            }
            @Override
            public void onFailure(Call<List<GetOfficialChallengeDto>> call, Throwable t) {
                System.out.println("공식 챌린지 정보: http통신 실패"+t.toString());
            }
        }));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Long checkedId = getOfficialChallengeList.get(position).getId();
                Boolean isJoin = getOfficialChallengeList.get(position).getIsJoin();

                if(isJoin == true){
                    Intent intent = new Intent(getActivity(),OfficialChallengeIng.class);
                    intent.putExtra("challengeId", checkedId);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), OfficialChallengeDetail.class);
                    intent.putExtra("challengeId", checkedId);
                    startActivity(intent);
                }
//                intent1.putExtra("challengeId1", checkedId);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<List<GetOfficialChallengeDto>> call_allOfficialChallenge = retrofitInterface.getAllOfficialChallenge(1L);
        call_allOfficialChallenge.enqueue((new Callback<List<GetOfficialChallengeDto>>() {
            @Override
            public void onResponse(Call<List<GetOfficialChallengeDto>> call, Response<List<GetOfficialChallengeDto>> response) {
                if(response.isSuccessful()){
                    try {
                        getOfficialChallengeList = response.body();
                        System.out.println("공식 챌린지 정보: 정보 업데이트 완료");
                        officialChallengeAdapter = new OfficialChallengeAdapter(getContext(), getOfficialChallengeList);

                        listView.setAdapter(officialChallengeAdapter);
                    }catch (Exception e){
                        System.out.println("공식 챌린지 정보: 정보 업데이트 실패");
                    }
                }else {
                    System.out.println("공식 챌린지 정보업데이트 :기타 예외 발생");
                }
            }
            @Override
            public void onFailure(Call<List<GetOfficialChallengeDto>> call, Throwable t) {
                System.out.println("공식 챌린지 정보: http통신 실패"+t.toString());
            }
        }));


    }
}