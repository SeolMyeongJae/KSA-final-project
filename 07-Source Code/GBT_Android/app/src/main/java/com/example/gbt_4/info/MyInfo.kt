package com.example.gbt_4.info

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.gbt_4.databinding.ActivityMyInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MyInfo : AppCompatActivity() {

    private lateinit var binding: ActivityMyInfoBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var pre: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                RESULT_OK -> {
//                    showToast("RESULT_OK")
                    val result = intent?.getStringExtra("name") ?: ""
//                    showToast(result)

                }
            }
        }
        pre = PreferenceManager.getDefaultSharedPreferences(this)
        editor = pre.edit()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.219.40.82/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.editInfo.setOnClickListener {
            val intent = Intent(this, UpdateInfo::class.java)
            getResult.launch(intent)
        }

        val service: UserInterface = retrofit.create(UserInterface::class.java)
        service.getUser(1).enqueue(object: Callback <UserDto> {
            override fun onFailure(call: Call<UserDto>, t:Throwable) {
                Log.d("log", t.message.toString())
                Log.d("log", "fail")
            }

            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    println(result.toString())

                    binding.username.text = pre.getString("username", "데이터가 없어요")
                    binding.birth.text = result?.birthYear.toString()
                    binding.smokingYear.text = result?.smokingYear.toString()
                    binding.average.text = result?.averageSmoking.toString()
                    binding.price.text = result?.price.toString()
                }
            }

        })

    }
}