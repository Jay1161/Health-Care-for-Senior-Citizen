package com.example.carewise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.VideoView;

public class AlaramActivity extends AppCompatActivity {

    VideoView videoView;
    MediaPlayer mediaPlayer;
    Vibrator v;

    //    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram);

        videoView = findViewById(R.id.video);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

        mediaPlayer.start();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 2
            v.vibrate(5000);
//            mediaPlayer.start();
        }
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alert));
        videoView.start();
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26

                    v.vibrate(5000);
                }
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alert + R.raw.sound));
                videoView.start();
                mediaPlayer.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        v.cancel();
        mediaPlayer.stop();
    }

}
