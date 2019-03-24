package com.example.mvptask.base;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mvptask.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void setToolbar(Toolbar toolbar, int toolbarBackgroundColor, String title, boolean showUpButton, boolean withElevation) {
        this.toolbar = toolbar;
        toolbar.setTitle(title);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, toolbarBackgroundColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && withElevation) {
            toolbar.setElevation(getResources().getDimension(R.dimen.padding_small));
        }
        setSupportActionBar(toolbar);

        if (showUpButton) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    protected abstract void initializeViews();

    protected abstract void setListeners();

    protected abstract void addFragment();
}
