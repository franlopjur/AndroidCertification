package com.franlopez.androidcertification.ui.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.franlopez.androidcertification.R;

public class HelloToastActivity extends AppCompatActivity {

    private Button showToastBtn;
    private Button increaseCounterBtn;
    private TextView counterLabel;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_toast);
        initializeViews();
        updateCounterIntoView();
    }

    private void updateCounterIntoView() {
        counterLabel.setText(String.valueOf(counter));
    }

    private void initializeViews() {
        showToastBtn = findViewById(R.id.hello_toast__btn__show_toast);
        increaseCounterBtn = findViewById(R.id.hello_toast__btn__increase_counter);
        counterLabel = findViewById(R.id.hello_toast__label__counter);

        showToastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        increaseCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCounter();
            }
        });
    }

    private void increaseCounter() {
        counter++;
        updateCounterIntoView();
    }

    private void showToast() {
        Toast.makeText(this, "Contador: " + counter, Toast.LENGTH_SHORT).show();
    }
}
