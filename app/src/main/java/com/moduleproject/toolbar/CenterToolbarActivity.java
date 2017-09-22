package com.moduleproject.toolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.center.toolbar.BaseToolbar;
import com.center.toolbar.TitleToolbar;
import com.moduleproject.R;


public class CenterToolbarActivity extends AppCompatActivity implements BaseToolbar.OnOptionItemClickListener {

    private TitleToolbar titleToolbar;
    private ActionBar mActionBar;
    private boolean visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_main);

        titleToolbar = (TitleToolbar) findViewById(R.id.toolbar);
        titleToolbar.setOnOptionItemClickListener(this);

        setSupportActionBar(titleToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(mActionBar.getDisplayOptions() | ActionBar.DISPLAY_HOME_AS_UP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_add:
                supportInvalidateOptionsMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionItemClick(View v) {
        switch (v.getId()) {
            case R.id.title_toolbar_back:
                finish();
                break;
            case R.id.title_toolbar_close:
                Toast.makeText(this, "关闭", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
