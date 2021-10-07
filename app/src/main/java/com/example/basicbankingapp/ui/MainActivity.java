package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;

import com.example.basicbankingapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_customers).setText("CUSTOMERS"), 0, false);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transactions).setText("TRANSACTIONS"), 1, false);
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(0)).getIcon()).setColorFilter(new PorterDuffColorFilter(getColor(R.color.base_theme1), PorterDuff.Mode.SRC_IN));
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(1)).getIcon()).setColorFilter(new PorterDuffColorFilter(getColor(R.color.base_theme1), PorterDuff.Mode.SRC_IN));
    }
}