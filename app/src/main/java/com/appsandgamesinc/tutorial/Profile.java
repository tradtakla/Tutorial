package com.appsandgamesinc.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity
{
    private String name;
    private TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getName();
    }

    private void getName()
    {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("username");
        System.out.println(name);
        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvProfile.setText(name);

    }
}