package com.example.gbt_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gbt_4.dto.GetCustomChallengeDto;

import java.util.List;

public class CustomChallengeUserListAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<GetCustomChallengeDto> getCustomChallengeList;
    private String photoURL;


    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
