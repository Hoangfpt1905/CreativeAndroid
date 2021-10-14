package com.hoangnd.creative;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.material.appbar.AppBarLayout;

import java.util.Calendar;

/**
 * Author Hoangnd 13/10/2021
 */

public class MainActivity extends AppCompatActivity {
    private ImageView ivTTS, ivMusic, ivFlashlight, iv3G;
    private AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        ivTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextToSpeechActivity.class);
                startActivity(intent);
            }
        });

        ivFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FlashLightActivity.class);
                startActivity(intent);
            }
        });


        iv3G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Notification3G.class);
                startActivity(intent);
            }
        });

        ivMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });


    }

    public void showDatePickerDialog(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, i2 + "/" + (+i1 + 1) + "/" + i, Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        date.show();
    }

    public void showTimePickerDialog(View view) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Toast.makeText(MainActivity.this, i + ":" + i1, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true);
        time.show();

    }

    public void initview() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        iv3G = (ImageView) findViewById(R.id.iv_activity_main_iv_3g);
        ivTTS = (ImageView) findViewById(R.id.iv_activity_main_iv_convert_text_to_speech);
        ivMusic = (ImageView) findViewById(R.id.iv_activity_main_iv_music);
        ivFlashlight = (ImageView) findViewById(R.id.iv_activity_main_iv_flashlight);

    }
}