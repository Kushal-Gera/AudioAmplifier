package com.example.audioamplifierspy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchViaDialReceiver extends BroadcastReceiver {

    private static final String LAUNCHER_NUMBER = "*#11*#";

    @Override
    public void onReceive(Context context, Intent intent) {

        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        if (LAUNCHER_NUMBER.equals(phoneNumber)) {
            setResultData(null);
            Intent appIntent = new Intent(context, ShowContent.class);
            appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(appIntent);
        }


    }



}
