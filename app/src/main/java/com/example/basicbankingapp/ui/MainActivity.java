package com.example.basicbankingapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.databinding.ActivityMainBinding;
import com.example.basicbankingapp.logic.MakeTransaction;
import com.example.basicbankingapp.logic.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private TabLayout tabLayout;
    private ViewPager2 mainScreen;
    private ViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;
    private ConstraintLayout welcomeScreen;
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        tabLayout = mainBinding.tabs;
        mainScreen = mainBinding.mainScreen;
        viewPagerAdapter = new ViewPagerAdapter(this,this);
        toolbar = mainBinding.appName;
        welcomeScreen = mainBinding.intro;
        initialize();
    }

    public void initialize(){
        Customers.updateDBIfDefaultNotFound(this);
        mainScreen.setAdapter(viewPagerAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, mainScreen, (tab, position) -> {
            if(position==0){
                tab.setIcon(R.drawable.ic_customers).setText("CUSTOMERS");
            } else {
                tab.setIcon(R.drawable.ic_transactions).setText("TRANSACTIONS");
            }
        });
        tabLayoutMediator.attach();

        setSupportActionBar(toolbar);
        mainScreen.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mainScreen.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                welcomeScreen.animate().scaleX(0).scaleY(0).setDuration(100).start();
            }
        };
        handler.postDelayed(runnable,2000);
    }

}