package com.example.gbt_4.info

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInterface {

    @GET("user/{userId}")
    fun getUser(@Path("userId") userId: Long): Call<UserDto>
}