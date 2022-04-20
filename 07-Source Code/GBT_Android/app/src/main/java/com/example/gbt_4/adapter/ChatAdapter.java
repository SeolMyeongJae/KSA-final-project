package com.example.gbt_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.ChatItem;
import com.example.gbt_4.R;
import com.example.gbt_4.dto.ChatItemDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatItem> data = null;
    private String photoURL;

    public ChatAdapter(ArrayList<ChatItem> chatList){
        data = chatList;
    }


    public class MyChat extends RecyclerView.ViewHolder{
        TextView content,time;

        MyChat(View itemView){
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.tv_my_chat_content);
            time = (TextView) itemView.findViewById(R.id.tv_my_chat_time);
        }
    }
    public class Chat extends RecyclerView.ViewHolder{
        TextView userName,content,time;
        ImageView photo;

        Chat(View itemView){
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.tv_chat_name);
            content = (TextView) itemView.findViewById(R.id.tv_chat_content);
            time = (TextView) itemView.findViewById(R.id.tv_chat_time);

            photo = (ImageView) itemView.findViewById(R.id.iv_chat_photo);

        }
    }



    @Override
    public int getItemViewType(int position) {
        return data.get(position).getChatType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == 1){
            view = inflater.inflate(R.layout.item_my_chat,parent,false);
            return new MyChat(view);
        } else {
            view = inflater.inflate(R.layout.item_chat,parent,false);
            return new Chat(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        if(viewHolder instanceof MyChat)
        {
            ((MyChat) viewHolder).content.setText(data.get(position).getChatItemDto().getMessage());
            ((MyChat) viewHolder).time.setText(""+(data.get(position).getChatItemDto().getCreated()));

        }
        else if(viewHolder instanceof Chat)
        {

            photoURL = data.get(position).getChatItemDto().getProfileImg();
            Glide.with(viewHolder.itemView).load(photoURL).into(((Chat) viewHolder).photo);


            ((Chat) viewHolder).userName.setText(data.get(position).getChatItemDto().getUserName());
            ((Chat) viewHolder).content.setText(data.get(position).getChatItemDto().getMessage());
            ((Chat) viewHolder).time.setText(""+(data.get(position).getChatItemDto().getCreated()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
