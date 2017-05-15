package com.moduleproject.numberpicker.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moduleproject.R;

public class SampleActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples2);

        findViewById(R.id.btnDark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleActivity.this, DarkThemeActivity.class));
            }
        });

        findViewById(R.id.btnLight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleActivity.this, LightThemeActivity.class));
            }
        });
    }
}
