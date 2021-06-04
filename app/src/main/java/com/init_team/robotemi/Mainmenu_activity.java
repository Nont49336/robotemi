package com.init_team.robotemi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.UserInfo;

import java.util.List;

public class Mainmenu_activity extends AppCompatActivity {
    Handler handler;
    Runnable r;

    private ImageView cs_call_img_btn;
    private ImageView emer_call_img_btn;
    private List Contact_Info;
    private UserInfo cs;
    private UserInfo emer;
    Robot robot;
    private ImageButton setting_btn;
    private ImageButton home_btn;



    public void onStart() {
        super.onStart();
        robot = robot.getInstance();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.fragment_fixpage);
        setting_btn = findViewById(R.id.setting_button_fixpage);
        home_btn = findViewById(R.id.home_button_fixpage);
        cs_call_img_btn = findViewById(R.id.customer_service_button_fixpage);
        emer_call_img_btn = findViewById(R.id.emergency_call_button_fixpage);
        change_menu_page_container(fragment_main_menu.newInstance());
        cs_call_img_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    UserInfo emer = robot.getAdminInfo();
                    robot.startTelepresence(emer.getName(), emer.getUserId());
                    return true;
                }
                return false;
            }
        });

        emer_call_img_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    emer = robot.getAdminInfo();
                    robot.startTelepresence(emer.getName(), emer.getUserId());
                    return true;

                }
                return false;
            }
        });
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                change_to_temiface();
            }
        };

        home_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    change_menu_page_container(fragment_main_menu.newInstance());
                }
                return false;
            }
        });

    }

    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();

    }

    public void stopHandler() {
        handler.removeCallbacks(r);
    }

    public void startHandler() {
        handler.postDelayed(r, 1000 * 1000);
    }

    private void change_to_temiface() {
        Intent intent = new Intent(Mainmenu_activity.this, Temiface_activity.class);
        startActivity(intent);
        finish();
    }

    public void change_menu_page_container(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.page_container_fixpage, fragment)
                .commit();
    }
    public void make_setting_visible()
    {
     setting_btn.setVisibility(View.VISIBLE);
     home_btn.setVisibility(View.INVISIBLE);
    }
    public void make_home_visible()
    {
        setting_btn.setVisibility(View.INVISIBLE);
        home_btn.setVisibility(View.VISIBLE);
    }
//    }

//    @Override
//    public void onRobotReady(boolean isReady) {
//        if (isReady) {
//            try {
//                final ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
//                robot.onStart(activityInfo);
//            } catch (PackageManager.NameNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    }
