package com.init_team.robotemi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.robotemi.sdk.Robot;

public class fragment_qr_thaichana extends Fragment {

    static fragment_qr_thaichana fragment;
    public static fragment_qr_thaichana newInstance()
    {
        if(fragment == null)
        {
            fragment = new fragment_qr_thaichana();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_thaichanapage,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ((Mainmenu_activity)getActivity()).make_home_visible();
    }

}
