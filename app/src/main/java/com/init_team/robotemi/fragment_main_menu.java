package com.init_team.robotemi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class fragment_main_menu extends Fragment {
    static fragment_main_menu fragment;
    public static fragment_main_menu newInstance() {
        if(fragment == null)
        {
            fragment = new fragment_main_menu(); 
        }
        return fragment;
    }
    private RecyclerView mRecyclerView;
    RecyclerAdapter menu_adapter;
    private List<menu_item> menu_item_lst = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_menupage_recycler,container,false);
        mRecyclerView = view.findViewById(R.id.pagelist_recyclerview_menupage);
        return view;
    }
    @SuppressLint("ClickableViewAccessibility")
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ((Mainmenu_activity) getActivity()).make_setting_visible();
        menu_item_lst.add(new menu_item(getResources().getString(R.string.qr_thai_chana), R.drawable.fragment_thaichana_icon_button, R.drawable.fragment_bg_button_orangered));
        menu_item_lst.add(new menu_item(getResources().getString(R.string.Event), R.drawable.fragment_event_icon_button, R.drawable.fragment_bg_button_redpurple));
        menu_item_lst.add(new menu_item(getResources().getString(R.string.Directory), R.drawable.fragment_directory_icon_button, R.drawable.fragment_bg_button_mintblue));
        menu_item_lst.add(new menu_item(getResources().getString(R.string.Rated_Us), R.drawable.fragment_ratedus_icon_button, R.drawable.fragment_bg_button_greybrown));
        menu_item_lst.add(new menu_item(getResources().getString(R.string.Promotion), R.drawable.fragment_promotion_icon_button, R.drawable.fragment_bg_button_orangered));
        menu_item_lst.add(new menu_item(getResources().getString(R.string.lang),R.drawable.fragment_language_icon_button,R.drawable.fragment_bg_button_redpurple));
        RecyclerAdapter menu_adapter = new RecyclerAdapter(this,menu_item_lst);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2,GridLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(menu_adapter);
    }
    public void onStop()
    {
        super.onStop();
        menu_item_lst.clear();
    }
}
