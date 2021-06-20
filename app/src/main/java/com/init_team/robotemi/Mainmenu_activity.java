package com.init_team.robotemi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.UserInfo;
import com.robotemi.sdk.listeners.OnTelepresenceEventChangedListener;
import com.robotemi.sdk.listeners.OnTelepresenceStatusChangedListener;
import com.robotemi.sdk.listeners.OnUserInteractionChangedListener;
import com.robotemi.sdk.model.CallEventModel;
import com.robotemi.sdk.telepresence.CallState;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Mainmenu_activity extends AppCompatActivity implements
        OnTelepresenceEventChangedListener {
    Handler handler;
    Runnable r;
    private ImageView cs_call_img_btn;
    private ImageView emer_call_img_btn;
    private UserInfo cs;
    Robot robot;
    private ImageButton setting_btn;
    private ImageButton home_btn;
    private List<UserInfo> all_contact;
    private TextView time_text;




    public void onStart() {
        super.onStart();
        robot = robot.getInstance();
        robot.hideTopBar();
        robot.addOnTelepresenceEventChangedListener(this);
        robot.getBatteryData();
    }
    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        loadLocale();
        setContentView(R.layout.fragment_fixpage);
        WindowManager manager = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        localLayoutParams.gravity = Gravity.TOP;
        getCurrentTime();
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
// this is to enable the notification to recieve touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
// Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        localLayoutParams.width = localLayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (55 * getResources().getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;
        customViewGroup view = new customViewGroup(this);
        manager.addView(view, localLayoutParams);
//        time_text.setText(getCurrentTime());
        setting_btn = findViewById(R.id.setting_button_fixpage);
        home_btn = findViewById(R.id.home_button_fixpage);
        cs_call_img_btn = findViewById(R.id.customer_service_button_fixpage);
        emer_call_img_btn = findViewById(R.id.emergency_call_button_fixpage);
        change_menu_page_container(fragment_main_menu.newInstance());
        cs_call_img_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showCScallDialog();
//                    cs = robot.getAdminInfo();
//                    robot.startTelepresence(cs.getName(), cs.getUserId());
                    return true;
                }
                return false;
            }
        });

        emer_call_img_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showEmercallDialog();
//                    robot.startTelepresence("Namwhan", "40a79d993531f4e830e5ec57c0a4b7c0");
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
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    change_menu_page_container(fragment_main_menu.newInstance());
                }
                return false;
            }
        });
        setting_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    finishAffinity();
                    System.exit(0);
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
        handler.postDelayed(r, 20 * 1000);
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

    public void make_setting_visible() {
        setting_btn.setVisibility(View.VISIBLE);
        home_btn.setVisibility(View.INVISIBLE);
    }

    public void make_home_visible() {
        setting_btn.setVisibility(View.INVISIBLE);
        home_btn.setVisibility(View.VISIBLE);
    }

    protected void onStop() {
        super.onStop();
        robot.removeOnTelepresenceEventChangedListener(this);
    }
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onTelepresenceEventChanged(@NotNull CallEventModel callEventModel) {
        if (callEventModel.getState() == 0) {
            stopHandler();
        }
        if (callEventModel.getState() == 1) {
            startHandler();
        }
    }

    public void showChangeLanguageDialog() {
        final String[] lang_list = {"Eng", "ไทย"};
        AlertDialog.Builder lang_builder = new AlertDialog.Builder(Mainmenu_activity.this);
        lang_builder.setTitle("Choose language...");
        lang_builder.setSingleChoiceItems(lang_list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("En");
                    recreate();
                } else if (i == 1) {
                    setLocale("th");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog lang_dialog = lang_builder.create();
        lang_dialog.show();

    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_lang", "");
        setLocale(language);
    }
    private void showCScallDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu_activity.this,R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Mainmenu_activity.this).inflate(R.layout.customerservice_popup,
                (ConstraintLayout) findViewById(R.id.constraintlayout_customerservice_popup));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.call_button_customerservice_popup).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    cs = robot.getAdminInfo();
                    robot.startTelepresence(cs.getName(), cs.getUserId());
                    alertDialog.dismiss();
                    return true; 
                }
                return false;
            }
        });
        view.findViewById(R.id.cancel_button_customerservice_popup).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    alertDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        if(alertDialog.getWindow() != null)
        {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
            alertDialog.show();

    }
    private void showEmercallDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu_activity.this,R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Mainmenu_activity.this).inflate(R.layout.emergencycall_popup,
                (ConstraintLayout) findViewById(R.id.constraintlayout_emergencycall_popup));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.call_button_emergencycall_popup).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    cs = robot.getAdminInfo();
                    robot.startTelepresence(cs.getName(), cs.getUserId());
                    alertDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        view.findViewById(R.id.cancel_button_emergencycall_popup).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    alertDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        if(alertDialog.getWindow() != null)
        {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
    private void getCurrentTime()
    {
         String time = new SimpleDateFormat("hh:mm",Locale.getDefault()).format(new Date());
         Log.e("time",time);
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
