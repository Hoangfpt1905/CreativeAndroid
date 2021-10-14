package com.hoangnd.creative;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Notification3G extends AppCompatActivity {
    private TextView tvDisplay;
    ConnectivityManager connectivityManager;
    NetworkInfo mywifi, my3G;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification3_g);

        tvDisplay = findViewById(R.id.tvDisplay);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        mywifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my3G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mywifi.isAvailable() == true) {
            tvDisplay.setText("You are connecting to the network using WIFI");
        } else if (my3G.isAvailable() == true) {
            tvDisplay.setText("You are connecting to the network using LTE");
        }

    }
}