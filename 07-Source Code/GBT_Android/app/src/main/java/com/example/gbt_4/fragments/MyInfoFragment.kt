package com.example.gbt_4.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import com.example.gbt_4.databinding.ActivityMyInfoBinding
import com.example.gbt_4.databinding.FragmentMyInfoBinding
import com.example.gbt_4.info.UpdateInfo
import com.example.gbt_4.info.UserDto
import com.example.gbt_4.info.UserInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyInfoFragment : Fragment() {

    private lateinit var binding: FragmentMyInfoBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        binding = FragmentMyInfoBinding.inflate(inflater, container, false)
//        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            when (it.resultCode) {
//                AppCompatActivity.RESULT_OK -> {
//                    val result = intent?.getStringExtra("name") ?: ""
//
//                }
//            }
//        }
        binding.btnEditInfo.setOnClickListener {
            var intent = Intent(context, UpdateInfo::class.java)

        }


        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.219.40.82/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        binding.editInfo.setOnClickListener {
//            val intent = Intent(this, UpdateInfo::class.java)
//
//            getResult.launch(intent)
//
//        }

        val service: UserInterface = retrofit.create(UserInterface::class.java)
        service.getUser(1).enqueue(object: Callback<UserDto> {
            override fun onFailure(call: Call<UserDto>, t:Throwable) {
                Log.d("log", t.message.toString())
                Log.d("log", "fail")
            }

            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    println(result.toString())

//                    binding.username.text = pre.getString("username", "데이터가 없어요")

                    binding.username.text = result?.userName.toString()
                    binding.birth.text = result?.birthYear.toString()
                    binding.smokingYear.text = result?.smokingYear.toString()
                    binding.average.text = result?.averageSmoking.toString()
                    binding.price.text = result?.price.toString()
                }
            }

        })
        return binding.root
    }

}