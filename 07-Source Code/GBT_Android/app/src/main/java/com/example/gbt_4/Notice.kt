package com.example.gbt_4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbt_4.adapter.InviteAdapter
import com.example.gbt_4.databinding.ActivityNoticeBinding

class Notice : AppCompatActivity() {
    private lateinit var binding : ActivityNoticeBinding

    private var adapter: InviteAdapter? = null
    private val data: ArrayList<InviteNotice> = ArrayList()

    init {
        instance = this
    }

    companion object{
        private var instance: Notice? = null
        fun getInstance(): Notice? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        adapter = InviteAdapter()
        adapter!!.invites = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            finish()
        }

        adapter!!.setItemClickListener(object: InviteAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                var intent = Intent(v.context, CustomChallengeDetail::class.java)

                startActivity(intent)
            }
        })
    }

    fun deleteNotice(inviteNotice : InviteNotice) {
        data.remove(inviteNotice)
        adapter?.notifyDataSetChanged()
    }

    private fun initialize() {
        with(data){
            add(InviteNotice("설명재", "금연ㄱㄱ"))
            add(InviteNotice("설명재2", "금연ㄱㄱㄱㄱㄱ"))
            add(InviteNotice("설명재3", "금연ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ"))
        }
    }
}