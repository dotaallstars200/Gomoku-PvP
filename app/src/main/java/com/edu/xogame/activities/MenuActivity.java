package com.edu.xogame.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.edu.xogame.R;
import com.edu.xogame.Utilities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.GetChars;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnExit, btnPlayWithBot, btnPlayWithFriend, btnHistory;
    private ProgressDialog dialog;
    Animation scale_up, scale_down,bounce;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer1;
    private static String TAG = "MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MultiPlayerActivity.disconnect(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnExit = findViewById(R.id.btnExit);
        btnPlayWithBot = findViewById(R.id.btnPlayWithBot);
        btnPlayWithFriend = findViewById(R.id.btnPlayWithFriend);
        btnHistory = findViewById(R.id.btnHistory);
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scale_down = AnimationUtils.loadAnimation(this,R.anim.scale_down);
        bounce = AnimationUtils.loadAnimation(this,R.anim.bounce);

        //animation của các bút bấm khi mờ lên.
        btnPlayWithBot.startAnimation(bounce);
        btnPlayWithFriend.startAnimation(bounce);
        btnHistory.startAnimation(bounce);
        btnExit.startAnimation(bounce);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.ring);

        mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.click);

        // animation của từng nút
        btnPlayWithBot.setOnTouchListener((v, event) -> {
            if(event.getAction()==MotionEvent.ACTION_UP)
            {
                btnPlayWithBot.startAnimation(scale_down);
            }else if(event.getAction()==MotionEvent.ACTION_DOWN){
                btnPlayWithBot.startAnimation(scale_up);
            }
            return false;
        });


        btnPlayWithBot.setOnClickListener(v -> {

           mediaPlayer1.start();

            Intent intent = new Intent(this, GamePlayActivity.class);
            dialog = ProgressDialog.show(this, "",
                    "Đang tải...", true);

            intent.putExtra("PlayType", "Bot");
            startActivityForResult(intent, Utilities.CANCEL_DIALOG);
        });


        btnHistory.setOnTouchListener((v, event) -> {
            if(event.getAction()==MotionEvent.ACTION_UP){
                btnHistory.startAnimation(scale_down);
            }else if (event.getAction()==MotionEvent.ACTION_DOWN){
                btnHistory.startAnimation(scale_up);
            }
            return false;
        });

        btnHistory.setOnClickListener(v -> {
           Intent intent = new Intent(this, GameHistoryActivity.class);
           startActivity(intent);
            mediaPlayer1.start();
        });



        btnExit.setOnTouchListener((v, event) -> {
            if(event.getAction()==MotionEvent.ACTION_UP){
                btnExit.startAnimation(scale_down);
            }else if (event.getAction()==MotionEvent.ACTION_DOWN){
                btnExit.startAnimation(scale_up);
            }
            return false;
        });


        btnExit.setOnClickListener(v -> {
            mediaPlayer1.start();
            System.exit(0);
        });

        btnPlayWithFriend.setOnTouchListener((v, event) -> {
            if(event.getAction()==MotionEvent.ACTION_UP){
                btnPlayWithFriend.startAnimation(scale_down);
            }else if(event.getAction()==MotionEvent.ACTION_DOWN){
                btnPlayWithFriend.startAnimation(scale_up);
            }
            return false;
        });
        btnPlayWithFriend.setOnClickListener(v -> {
            mediaPlayer1.start();
            Intent intent = new Intent(this, MultiPlayerActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utilities.CANCEL_DIALOG && resultCode == RESULT_CANCELED) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected void onPostResume() {
        mediaPlayer.start();
        super.onPostResume();
    }

    @Override
    protected void onPause() {
       mediaPlayer.stop();
        super.onPause();
    }
}