package com.moduleproject.datepicker.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moduleproject.R;

public class DatePickerSamples extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);

        findViewById(R.id.btnLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatePickerSamples.this, LayoutSample.class));
            }
        });

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatePickerSamples.this, DialogSample.class));
            }
        });
    }
}
