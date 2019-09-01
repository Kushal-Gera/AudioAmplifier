package com.example.audioamplifierspy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean entered_till;
    private ImageView tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        entered_till = preferences.getBoolean("entered", false);

        findViewById(R.id.imageView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                  if (!entered_till)
                      startActivity(new Intent(MainActivity.this, InputACT.class));
                  return true;
            }
        });
        
        SeekBar sb = findViewById(R.id.seekBar);
        SeekBar sb2 = findViewById(R.id.seekBar2);
        SeekBar sb3 = findViewById(R.id.seekBar3);

        sb.setProgress(80);
        sb2.setProgress(80);
        sb3.setProgress(50);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 100){
                    Toast.makeText(MainActivity.this, "Volume Has Been Set To its Max Value", Toast.LENGTH_SHORT).show();
                    Snackbar.make(seekBar, "Turn Device Volume To Full To See Difference", Snackbar.LENGTH_INDEFINITE).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sb.setOnSeekBarChangeListener(listener);
        sb2.setOnSeekBarChangeListener(listener);
        sb3.setOnSeekBarChangeListener(listener);


    }




}
