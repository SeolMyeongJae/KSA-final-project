package com.example.gbt_4;

import com.example.gbt_4.dto.AddCustomChallengeDto;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.AddUserDto;
import com.example.gbt_4.dto.GetCustomChallengeDto;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;
import com.example.gbt_4.dto.UserChallengeDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
//유저 인터페이스
    @GET("user/{userId}")
    Call<GetUserDto> getByUserId(@Path("userId") Long userId);

    @POST("user")
    Call<Integer> addUser(@Body AddUserDto addUserDto);

//흡연정보 인터페이스
    @POST("smoking")
    Call<Long> addSmoking(@Body AddSmokingDto addSmokingDto);

    @GET("smoking/all/this-month/user/{userId}")
    Call<GetSmokingListDto> getMonthCount(@Path("userId") Long userId);

    @GET("smoking/today/{userId}")
    Call<GetSmokingDto> getTodayCount(@Path("userId") Long userId);

//공식 챌린지 인터페이스
    @GET("challenge/all/{userId}")
    Call<List<GetOfficialChallengeDto>> getAllOfficialChallenge(@Path("userId") Long userId);

    @GET("challenge/{id}")
    Call<GetOfficialChallengeDto> getOfficialChallenge(@Path("id") Long id);

    @POST("user-challenge")
    Call<Integer> participateOfficialChallenge(@Body UserChallengeDto userChallengeDto);

//커스텀 챌린지 인터페이스
    @GET("custom/all/{userId}")
    Call<List<GetCustomChallengeDto>> getCustomChallengeByUserId(@Path("userId") Long id);
//    Call<ResponseBody> getCustomChallengeByUserId(@Path("userId") Long id);

    @GET("custom/{id}")
    Call<GetCustomChallengeDto> getCustomChallengeById(@Path("id") Long id);

    @POST("custom")
    Call<Integer> addCustomChallenge(@Body AddCustomChallengeDto addCustomChallengeDto);




}
