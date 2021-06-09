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
    private List<menu_item> menu_item_lst;
    View gridview;
    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_menupage_recycler,container,false);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        menu_item_lst.add(new menu_item("Thai Chana",R.drawable.fragment_thaichana_icon_button));
        menu_item_lst.add(new menu_item("Directory",R.drawable.fragment_directory_icon_button));
        menu_item_lst.add(new menu_item("Promotion",R.drawable.fragment_event_icon_button));
        menu_item_lst.add(new menu_item("Event",R.drawable.fragment_event_icon_button));
        menu_item_lst.add(new menu_item("Rated Us",R.drawable.fragment_ratedus_icon_button));
        mRecyclerView = getView().findViewById(R.id.pagelist_recyclerview_menupage);
        RecyclerAdapter menu_adapter = new RecyclerAdapter(this,menu_item_lst);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        mRecyclerView.setAdapter(menu_adapter);

    }
}
