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


    public void onStart() {
        super.onStart();

    }

    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceStated) {
//        Robot robot = new Robot(getApplicationContext());
        super.onCreate(savedInstanceStated);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.fragment_fixpage);
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
//            cs_call_img_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    emer = robot.getAdminInfo();
//                    robot.startTelepresence(emer.getName(),emer.getUserId());
//                }
//            });
//            emer_call_img_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    robot.startTelepresence(emer.getName(),emer.getUserId());
//                }
//            });
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
        startHandler();
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

    public void setting_and_home_btn_visibility()
    {
        if(getSupportFragmentManager().findFragmentById(R.id.page_container_fixpage) instanceof fragment_main_menu)
        {
//            Log.i("TAG", )
        }
    }

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