package com.init_team.robotemi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
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

    }

    @Override
    public int getItemCount()
    {
        return mMenu_item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menu_title;
        ImageButton menu_pic_id;
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
