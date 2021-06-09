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
    menu_item menu_item_data = new menu_item();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menupage_recycler,container,false);
        mRecyclerView = getView().findViewById(R.id.pagelist_recyclerview_menupage);
        return view;
    }
    @SuppressLint("ClickableViewAccessibility")
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        menu_item_data.add("Thai Chana",R.drawable.fragment_thaichana_icon_button);
        menu_item_lst.add(menu_item_data);
        Log.e("test",menu_item_lst.toString());
        RecyclerAdapter menu_adapter = new RecyclerAdapter(this,menu_item_lst);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        mRecyclerView.setAdapter(menu_adapter);

    }
}
