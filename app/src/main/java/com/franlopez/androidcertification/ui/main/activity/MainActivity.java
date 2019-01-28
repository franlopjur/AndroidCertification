package com.franlopez.androidcertification.ui.main.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.commons.ResourceUtils;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.ui.viewmodel.SearchRepoViewModel;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //region Members
    private SearchRepoViewModel searchRepoViewModel;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private EditText queryInput;
    private TextView resultsLabel;
    private long timeStampToLastModificationIntoInput;

    private final static int TIME_TO_LAUNCH_REQUEST = 2000;
    private final static int MIN_CHARS_TO_LAUNCH_REQUEST = 3;
    //endregion

    //region Public Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRepoViewModel.updateQuery(queryInput.getText().toString());
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        registerObserverIntoLiveData();
        setUpTextWatcher();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion

    //region Private Methods
    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        queryInput = findViewById(R.id.main__input__query);
        resultsLabel = findViewById(R.id.main__label__results);
    }

    private void registerObserverIntoLiveData() {
        searchRepoViewModel = ViewModelProviders.of(this).get(SearchRepoViewModel.class);
        searchRepoViewModel.getSearchReposLiveData().observe(this, new Observer<List<GithubRepoDomain>>() {
            @Override
            public void onChanged(@Nullable List<GithubRepoDomain> repoDomains) {
                resultsLabel.setText(ResourceUtils.getStringFormat(R.string.results_format, repoDomains.size()));
            }
        });
        searchRepoViewModel.getErrorMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpTextWatcher() {
        queryInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) &&
                        charSequence.length() > MIN_CHARS_TO_LAUNCH_REQUEST &&
                        isGreaterThanMaxTimestamp()) {
                    setTimestamp();
                    searchRepoViewModel.updateQuery(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean isGreaterThanMaxTimestamp() {
        return (Calendar.getInstance().getTimeInMillis() - timeStampToLastModificationIntoInput)
                > TIME_TO_LAUNCH_REQUEST;
    }

    private void setTimestamp() {
        timeStampToLastModificationIntoInput = Calendar.getInstance().getTimeInMillis();
    }
    //endregion
}
