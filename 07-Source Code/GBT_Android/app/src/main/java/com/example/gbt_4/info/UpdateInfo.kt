package com.example.gbt_4.info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gbt_4.databinding.ActivityUpdateInfoBinding

class UpdateInfo : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data2", "설명재2")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}