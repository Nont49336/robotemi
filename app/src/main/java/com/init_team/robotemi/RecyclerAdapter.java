package com.init_team.robotemi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context contxt;
    private fragment_main_menu fragment_main_menu;
    private List<menu_item> mMenu_item;
    LayoutInflater inflater;

    public RecyclerAdapter(fragment_main_menu fragment_main_menu, List<menu_item> mMenu_item)
    {
        this.fragment_main_menu = fragment_main_menu;
        this.mMenu_item = mMenu_item;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       LayoutInflater mInflater = LayoutInflater.from(fragment_main_menu.getContext());
       view = mInflater.inflate(R.layout.button_cardview_menupage,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.menu_title.setText(mMenu_item.get(position).getMenu_title());
        holder.menu_pic_id.setImageResource(mMenu_item.get(position).getMenu_pic_id());
        holder.cardView.setBackgroundResource(mMenu_item.get(position).getMenu_bg_id());
        holder.cardView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(position == 0) {((Mainmenu_activity) v.getContext()).change_menu_page_container(fragment_qr_thaichana.newInstance());}
//                  if(position == 1) {((Mainmenu_activity) v.getContext()).change_menu_page_container(.newInstance());}
                    if(position == 2) {((Mainmenu_activity) v.getContext()).change_menu_page_container(fragment_directorypage.newInstance());}
                    if(position == 3) {((Mainmenu_activity) v.getContext()).change_menu_page_container(fragment_rateduspage.newInstance());}
//                    if(position == 4) {((Mainmenu_activity) v.getContext()).change_menu_page_container(fragment_promotion.newInstance());}
//                    if(position == 5){((Mainmenu_activity) v.getContext()).showChangeLanguageDialog();}
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mMenu_item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menu_title;
        ImageView menu_pic_id;
        CardView cardView;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            menu_title = itemView.findViewById(R.id.button_text_menupage);
            menu_pic_id  = itemView.findViewById(R.id.button_icon_menupage);
            cardView = itemView.findViewById(R.id.button_cardview_menupage);
        }
    }
}
