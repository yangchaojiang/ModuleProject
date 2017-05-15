package com.moduleproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moduleproject.calendarview.samples.CalendarSamples;
import com.moduleproject.datepicker.samples.DatePickerSamples;
import com.moduleproject.numberpicker.samples.SampleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Datepicker).setOnClickListener(this);
        findViewById(R.id.numpicker).setOnClickListener(this);
        findViewById(R.id.calendarview).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Datepicker:
                Intent intent=new Intent(this,DatePickerSamples.class);
                startActivity(intent);
                break;
            case R.id.numpicker:
                  intent=new Intent(this,SampleActivity.class);
                startActivity(intent);
                break;
            case R.id.calendarview:
                  intent=new Intent(this,CalendarSamples.class);
                startActivity(intent);
                break;
        }

    }
}
