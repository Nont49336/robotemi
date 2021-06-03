package com.init_team.robotemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_temiface extends Fragment
// this should be testing one
{

    static  fragment_temiface fragment;
    public static fragment_temiface newInstance(){
       if(fragment == null){
           fragment = new fragment_temiface();
       }
       return fragment;
    }

    private VideoView temiface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_facepoomjaibotpage,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        temiface = getView().findViewById(R.id.eye_video_facepoomjaibotpage);

        temiface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeMain_Menu();
            }
        });
    }


}
