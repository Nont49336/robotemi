package com.init_team.robotemi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.robotemi.sdk.BatteryData;
import com.robotemi.sdk.NlpResult;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.UserInfo;
import com.robotemi.sdk.activitystream.ActivityStreamPublishMessage;
import com.robotemi.sdk.listeners.OnBatteryStatusChangedListener;
import com.robotemi.sdk.listeners.OnBeWithMeStatusChangedListener;
import com.robotemi.sdk.listeners.OnConstraintBeWithStatusChangedListener;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

//should have preloader for faster application
public class MainActivity extends AppCompatActivity implements
        Robot.NlpListener,
        OnRobotReadyListener,
        Robot.ConversationViewAttachesListener,
        Robot.WakeupWordListener,
        Robot.ActivityStreamPublishListener,
        Robot.TtsListener,
        OnBeWithMeStatusChangedListener,
        OnGoToLocationStatusChangedListener,
        OnLocationsUpdatedListener,
        OnBatteryStatusChangedListener,
        OnConstraintBeWithStatusChangedListener,
        OnDetectionStateChangedListener
{
    FrameLayout main_container;
    Robot robot;

//    private Button call_btn;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static String PACKAGE_NAME;
    private static final String[] PERMISSIONS_STORAGE =
    {
        Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE

    };
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        Robot robot = new Robot(getApplicationContext());
        PACKAGE_NAME = getApplicationContext().getPackageName();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_container = findViewById(R.id.main_layout);

//        call_btn = findViewById(R.id.call_btn);
//        call_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                robot.getAllContact();
//                robot.startTelepresence(robot.getAdminInfo().getName(),robot.getAdminInfo().getUserId());
//            }
//        });


//        changelayout(fragment_temiface.newInstance());

        if (isNetwork(getApplicationContext())){

            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "Internet Is Not Connected", Toast.LENGTH_SHORT).show();
        }
          changeMain_Menu();
    }

    public void change_temiface()
    {
        Intent intent = new Intent(MainActivity.this,Temiface_activity.class);
        startActivity(intent);

    }
    public void changelayout(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout,fragment)
                .commit();
    }
    public void changeMain_Menu(){
        Intent intent = new Intent(MainActivity.this,Mainmenu_activity.class);
        startActivity(intent);
        finish();
    }

    public boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public void onPublish(@NotNull ActivityStreamPublishMessage activityStreamPublishMessage) {

    }

    @Override
    public void onConversationAttaches(boolean b) {

    }

    @Override
    public void onNlpCompleted(@NotNull NlpResult nlpResult) {

    }

    @Override
    public void onTtsStatusChanged(@NotNull TtsRequest ttsRequest) {

    }

    @Override
    public void onWakeupWord(@NotNull String s, int i) {

    }

    @Override
    public void onBeWithMeStatusChanged(@NotNull String s) {

    }

    @Override
    public void onGoToLocationStatusChanged(@NotNull String s, @NotNull String s1, int i, @NotNull String s2) {

    }

    @Override
    public void onLocationsUpdated(@NotNull List<String> list) {

    }

    protected void onStart() {
        super.onStart();
        robot = robot.getInstance(); // get an instance of the robot in order to begin using its features.
        robot.addOnRobotReadyListener(this);
//        robot.addNlpListener(this);
        robot.addOnBeWithMeStatusChangedListener(this);
        robot.addOnGoToLocationStatusChangedListener(this);
        robot.addConversationViewAttachesListenerListener(this);
//        robot.addWakeupWordListener(this);
        robot.addTtsListener(this);
        robot.addOnLocationsUpdatedListener(this);
        robot.addOnBatteryStatusChangedListener(this);
        robot.addOnConstraintBeWithStatusChangedListener(this);
        robot.addOnDetectionStateChangedListener(this);

    }

    protected void onStop() {
        super.onStop();
        robot.removeOnRobotReadyListener(this);
        robot.removeNlpListener(this);
        robot.removeOnBeWithMeStatusChangedListener(this);
        robot.removeOnGoToLocationStatusChangedListener(this);
        robot.removeConversationViewAttachesListenerListener(this);
        robot.removeWakeupWordListener(this);
        robot.removeTtsListener(this);
        robot.removeOnLocationsUpdateListener(this);
    }
    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            try {
                final ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                robot.onStart(activityInfo);
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onBatteryStatusChanged(@org.jetbrains.annotations.Nullable BatteryData batteryData) {

    }

    @Override
    public void onConstraintBeWithStatusChanged(boolean b) {

    }

    @Override
    public void onDetectionStateChanged(int i) {

    }
}
