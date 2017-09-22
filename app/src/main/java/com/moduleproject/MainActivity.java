package com.moduleproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.loginmodule.LoginActivity;
import com.loginmodule.TabLoginActivity;
import com.moduleproject.datepicker.samples.DatePickerSamples;
import com.moduleproject.numberpicker.samples.SampleActivity;
import com.moduleproject.state.StateActivity;
import com.moduleproject.toolbar.CenterToolbarActivity;


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
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
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
            case R.id.button:
                intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent=new Intent(this,TabLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent=new Intent(this,StateActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent=new Intent(this,CenterToolbarActivity.class);
                startActivity(intent);
                break;
        }

    }
}
