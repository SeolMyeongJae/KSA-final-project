package com.example.gbt_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;
import com.example.gbt_4.fragments.ChallengeFragment;
import com.example.gbt_4.fragments.CommunityFragment;
import com.example.gbt_4.fragments.HomeFragment;
import com.example.gbt_4.fragments.MyInfoFragment;
import com.example.gbt_4.fragments.StatisticsFragment;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage extends AppCompatActivity{

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

    private FragmentManager fragmentManager;


    private Fragment fh,fc,fs,fm;
//    // Fragments 선언
//    ChallengeFragment fc = new ChallengeFragment();
//    HomeFragment fh = new HomeFragment();
//    StatisticsFragment fs = new StatisticsFragment();
//    MyInfoFragment fm = new MyInfoFragment();
////    CommunityFragment communityFragment = new CommunityFragment();
    // 하단 Navi bar 선언
    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_page);



        fragmentManager = getSupportFragmentManager();

        //처음 화면 설정
        fh = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_lo,fh).commit();

        bottomNavigationView = (NavigationBarView) findViewById(R.id.bottom_navi);

        //알림 페이지 이동
        Button btn_notice = (Button) findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Notice.class);
                startActivity(intent);
            }
        });

        // 하단 Navi bar 구현
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_challenge:
                            fc = new ChallengeFragment();
                            fragmentManager.beginTransaction().replace(R.id.frame_lo,fc).commit();
                        break;
//                    case R.id.tab_community:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, communityFragment).commit();
//                        break;
                    case R.id.tab_home:
                        fh = new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.frame_lo, fh).commit();
                        break;
                    case R.id.tab_info:
                            fm = new MyInfoFragment();
                            fragmentManager.beginTransaction().replace(R.id.frame_lo, fm).commit();
                        break;
                    case R.id.tab_statistic:
                            fs = new StatisticsFragment();
                            fragmentManager.beginTransaction().replace(R.id.frame_lo, fs).commit();
                        break;
                }
                return true;
            }
        });

    }
}