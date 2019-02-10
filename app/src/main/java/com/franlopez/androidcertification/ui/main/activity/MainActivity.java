package com.franlopez.androidcertification.ui.main.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.ui.main.GithubRepoAdapter;
import com.franlopez.androidcertification.ui.viewmodel.SearchRepoViewModel;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //region Members
    private SearchRepoViewModel searchRepoViewModel;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private EditText queryInput;
    private RecyclerView resultsList;
    private ProgressBar loadingIndicator;

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
        setUpMenuAndFab();
        registerObserverIntoLiveData();
        setUpTextWatcher();
        setUpRecycler();
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
        resultsList = findViewById(R.id.main__list__elements);
        loadingIndicator = findViewById(R.id.main__progress__loading);
    }

    private void registerObserverIntoLiveData() {
        searchRepoViewModel = ViewModelProviders.of(this).get(SearchRepoViewModel.class);
        searchRepoViewModel.getSearchReposLiveData().observe(this, new Observer<PagedList<GithubRepoDomain>>() {
            @Override
            public void onChanged(@Nullable PagedList<GithubRepoDomain> repoDomains) {
                if (repoDomains != null &&
                        resultsList != null &&
                        resultsList.getAdapter() != null &&
                        resultsList.getAdapter() instanceof PagedListAdapter) {
                    setLoading(false);
                    ((PagedListAdapter) resultsList.getAdapter()).submitList(repoDomains);
                }
            }
        });
        searchRepoViewModel.getErrorMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                setLoading(false);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoading(boolean loading) {
        if (loading) {
            resultsList.setVisibility(View.GONE);
            loadingIndicator.setVisibility(View.VISIBLE);

        } else {
            resultsList.setVisibility(View.VISIBLE);
            loadingIndicator.setVisibility(View.GONE);
        }
    }

    private void setUpTextWatcher() {
        queryInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String query = textView.getText().toString();
                    if (!TextUtils.isEmpty(query) &&
                            query.length() > MIN_CHARS_TO_LAUNCH_REQUEST &&
                            isGreaterThanMaxTimestamp()) {
                        setLoading(true);
                        setTimestamp();
                        if (resultsList != null &&
                                resultsList.getAdapter() != null &&
                                resultsList.getAdapter() instanceof PagedListAdapter) {
                            ((PagedListAdapter) resultsList.getAdapter()).submitList(null);
                        }
                        searchRepoViewModel.updateQuery(query);
                    }
                }
                return false;
            }
        });
        queryInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String query = queryInput.getText().toString();
                    if (!TextUtils.isEmpty(query) &&
                            query.length() > MIN_CHARS_TO_LAUNCH_REQUEST &&
                            isGreaterThanMaxTimestamp()) {
                        setLoading(true);
                        setTimestamp();
                        searchRepoViewModel.updateQuery(query);
                    }
                }
                return false;
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

    private void setUpMenuAndFab() {
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoading(true);
                searchRepoViewModel.updateQuery(queryInput.getText().toString());
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpRecycler() {
        resultsList.setAdapter(new GithubRepoAdapter(new DiffUtil.ItemCallback<GithubRepoDomain>() {
            @Override
            public boolean areItemsTheSame(@NonNull GithubRepoDomain githubRepoDomain, @NonNull GithubRepoDomain t1) {
                return githubRepoDomain.getId() == t1.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull GithubRepoDomain githubRepoDomain, @NonNull GithubRepoDomain t1) {
                return !TextUtils.isEmpty(githubRepoDomain.getDescription()) &&
                        githubRepoDomain.getDescription().equalsIgnoreCase(t1.getDescription());
            }
        }));
    }
    //endregion
}
