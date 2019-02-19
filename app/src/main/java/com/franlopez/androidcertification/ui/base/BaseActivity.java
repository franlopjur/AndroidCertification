package com.franlopez.androidcertification.ui.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import com.franlopez.androidcertification.R;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentLayout();
    protected abstract void initializeViews();
    protected abstract void fabActionBehaviour();
    protected abstract boolean isFabVisible();

    private ViewStub contentContainer;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setUpContentLayout();
        initializeViews();
        setUpActionbar();
        setUpFab();
    }

    private void setUpFab() {
        fab = findViewById(R.id.base__btn__action);
        if (isFabVisible()) {
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fabActionBehaviour();
                }
            });
        } else {
            fab.hide();
        }
    }

    private void setUpActionbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpContentLayout() {
        contentContainer = findViewById(R.id.base__content_container);
        contentContainer.setLayoutResource(getContentLayout());
        contentContainer.inflate();
    }
}
