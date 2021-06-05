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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

public class fragment_main_menu extends Fragment {
    static fragment_main_menu fragment;
    public static fragment_main_menu newInstance() {
        if(fragment == null)
        {
            fragment = new fragment_main_menu(); 
        }
        return fragment;
    }

    private CardView thai_chana_btn;
    private CardView directory_btn;
    private CardView promotion_btn;
    private CardView event_btn;
    private CardView rated_us_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_menupage,container,false);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        thai_chana_btn = getView().findViewById(R.id.thaichana_cardview_menupage);
        directory_btn = getView().findViewById(R.id.directory_cardview_menupage);
        promotion_btn = getView().findViewById(R.id.promotion_cardview_menupage);
        event_btn = getView().findViewById(R.id.event_cardview_menupage);
        rated_us_btn = getView().findViewById(R.id.rated_us_cardview_menupage);
        ((Mainmenu_activity)getActivity()).make_setting_visible();
        thai_chana_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                ((Mainmenu_activity) getActivity()).change_menu_page_container(fragment_qr_thaichana.newInstance());
                Log.e("TAG", "It is touched down");

            }
                return false;
            }
        });
//        thai_chana_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((Mainmenu_activity) getActivity()).change_menu_page_container(fragment_qr_thaichana.newInstance());
//                Log.e("TAG", "It is touched down");
//            }
//        });
        directory_btn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
//                    ((Mainmenu_activity) getActivity()).change_menu_page_container(fragment_event);
//                    return true;
                }
                return false;
            }
        });
        rated_us_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ((Mainmenu_activity) getActivity()).change_menu_page_container(fragment_rateduspage.newInstance());
                }
                return false;
            }
        });
    }
}
