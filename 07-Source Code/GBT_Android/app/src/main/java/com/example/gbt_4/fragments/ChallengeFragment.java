package com.example.gbt_4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gbt_4.R;
import com.example.gbt_4.fragments.CustomChallengeFragment;
import com.example.gbt_4.fragments.OfficialChallengeFragment;
import com.example.gbt_4.info.PracticeDto;

public class ChallengeFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenge, container, false);

        ViewGroup officialChallengeLayout = (ViewGroup) v.findViewById(R.id.lo_official_challenge);
        ViewGroup customeChallengeLayout = (ViewGroup) v.findViewById(R.id.lo_custom_challenge);

        officialChallengeLayout.setOnClickListener(this);
        customeChallengeLayout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.lo_official_challenge:
                OfficialChallengeFragment officialChallengeFragment = new OfficialChallengeFragment();
                fragmentTransaction.replace(R.id.frame_lo, officialChallengeFragment).addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.lo_custom_challenge:
                CustomChallengeFragment customChallengeFragment = new CustomChallengeFragment();
                fragmentTransaction.replace(R.id.frame_lo, customChallengeFragment).addToBackStack(null);
                fragmentTransaction.commit();
                break;

            default:
                break;
        }
    }
}