package com.example.audioamplifierspy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputACT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_act);

        SharedPreferences preferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //remember to change false to true
        editor.putBoolean("entered", false);
        editor.apply();

        //**********************************************************************************
        EditText et = findViewById(R.id.number);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save it
                Toast.makeText(InputACT.this, "saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InputACT.this, MainActivity.class));
                finish();

            }
        });


    }


    @Override
    public void onBackPressed() {
        return;

    }
}
