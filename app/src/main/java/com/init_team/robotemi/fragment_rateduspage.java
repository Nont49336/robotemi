package com.init_team.robotemi;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

import java.sql.DatabaseMetaData;

public class fragment_rateduspage extends Fragment {
    static fragment_rateduspage fragment;
    public fragment_rateduspage newInstance()
    {
        if(fragment == null)
        {
            fragment = new fragment_rateduspage();
        }
        return fragment;
    }
    private int rating_score;
    private String robot_comment;
    private RatingBar rated_bar;
    private TextView comment_textbox;
    private ImageButton submit_btn;
    private Review review;
    private DatabaseReference temi_robot_db_rating_ref ;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_rateduspage,container,false);
    }
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        rated_bar = getView().findViewById(R.id.starbar_rateduspage);
        comment_textbox = getView().findViewById(R.id.review_edittext_rateduspage);
        submit_btn = getView().findViewById(R.id.submit_button_rateduspage);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating_score = ((int) rated_bar.getRating());
                robot_comment = comment_textbox.getText().toString();
                review.setRate_score(rating_score);
                review.setComment(robot_comment);
                temi_robot_db_rating_ref = FirebaseDatabase.getInstance().getReference().child("rating table");
                temi_robot_db_rating_ref.push().setValue(review);
                Toast.makeText(getActivity(),"THANK YOU",Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
