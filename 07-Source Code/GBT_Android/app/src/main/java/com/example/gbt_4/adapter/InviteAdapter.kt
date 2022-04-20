package com.example.gbt_4.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gbt_4.InviteNotice
import com.example.gbt_4.Notice
import com.example.gbt_4.R
import com.example.gbt_4.databinding.ActivityNoticeBinding
import com.example.gbt_4.databinding.ItemInviteBinding

class InviteAdapter : RecyclerView.Adapter<InviteAdapter.ViewHolder>() {
    var invites = ArrayList<InviteNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInviteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val invite = invites[position]
        holder.setItem(invite)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount() = invites.size

    inner class ViewHolder(val binding: ItemInviteBinding) : RecyclerView.ViewHolder(binding.root) {
        private val noticeActivity = Notice.getInstance()
        var inviteNotice : InviteNotice? = null
        init {
            binding.btnRemove.setOnClickListener {
                noticeActivity?.deleteNotice(inviteNotice!!)
            }
        }

        fun setItem(invite: InviteNotice) {
            this.inviteNotice = invite
            binding.tvCaller.text = invite.caller
            binding.tvTitle.text = invite.title
        }
    }

}