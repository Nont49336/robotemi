package com.init_team.robotemi;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.robotemi.sdk.Robot;

import java.io.IOException;

// declaration of permission and layout should be in temiface?
public class Temiface_activity extends AppCompatActivity
{

    //permission
    private static final String ACTION_HOME_WELCOME = "home.welcome", ACTION_HOME_DANCE = "home.dance", ACTION_HOME_SLEEP = "home.sleep";
    private static final String HOME_BASE_LOCATION = "home base";
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final int REQUEST_CODE_NORMAL = 0;
    private static final int REQUEST_CODE_FACE_START = 1;
    private static final int REQUEST_CODE_FACE_STOP = 2;
    private static final int REQUEST_CODE_MAP = 3;
    private static final int REQUEST_CODE_SEQUENCE_FETCH_ALL = 4;
    private static final int REQUEST_CODE_SEQUENCE_PLAY = 5;
    private static final int REQUEST_CODE_START_DETECTION_WITH_DISTANCE = 6;
    private static final int REQUEST_CODE_SEQUENCE_PLAY_WITHOUT_PLAYER = 7;
    private static final int REQUEST_CODE_GET_MAP_LIST = 8;

    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private Context sound_context=this;
    //end of permission declaration
    private Robot robot;
    private VideoView temiface;
    private MediaPlayer sound_player = new MediaPlayer();
//    private MediaController vid_player = new MediaController(this);

//    private Uri sound_uri = Uri.parse("android.resources://"+getPackageName()+"/"+R.raw.poomjaibot_sound);

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int rawId = getResources().getIdentifier("poomjaibot_eye_face",  "raw", getPackageName());
      //  Uri vid_uri = Uri.parse("android.resources://"+getPackageName()+"/"+rawId);
        super.onCreate(savedInstanceState);
        temiface = findViewById(R.id.eye_video_facepoomjaibotpage);
        setContentView(R.layout.fragment_facepoomjaibotpage); // poomjai_bot face as 1 activity for vid switching and sound switching.;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      //  vid_player.setAnchorView(temiface);
      //  temiface.setMediaController(vid_player);
      //  temiface.setVideoURI(vid_uri);
       // temiface.requestFocus();
       // temiface.start();
        sound_player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        sound_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
            }
        });
//        temiface.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS)
//                {
//                    switch_to_mainmenu_activity();
//                    return true;
//                }
//
//                return false;
//            }
//        });
        play_sound(R.raw.poomjaibot_sound);
    }

    private void switch_to_mainmenu_activity()
    {
         Intent switch_activity_intent = new Intent(this, Mainmenu_activity.class);
         startActivity(switch_activity_intent);
    }
    public MediaPlayer play_sound(@RawRes int RawresId)
    {
        try {
            AssetFileDescriptor afd = sound_context.getResources().openRawResourceFd(RawresId);
            if (afd == null) return null;

            sound_player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            sound_player.prepareAsync();
            return sound_player;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


     // place app on the top bar for quick access shortcut
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
}