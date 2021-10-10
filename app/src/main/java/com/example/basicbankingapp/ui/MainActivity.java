package com.example.basicbankingapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.databinding.ActivityMainBinding;
import com.example.basicbankingapp.logic.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
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
        viewPager2 = mainBinding.mainScreen;
        viewPagerAdapter = new ViewPagerAdapter(this);
        toolbar = mainBinding.appName;
        welcomeScreen = mainBinding.intro;
        initialize();
    }

    public void initialize(){
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_customers).setText("CUSTOMERS"), 0, false);
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transactions).setText("TRANSACTIONS"), 1, false);
//        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(0)).getIcon()).setColorFilter(new PorterDuffColorFilter(getColor(R.color.base_theme1), PorterDuff.Mode.SRC_IN));
//        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(1)).getIcon()).setColorFilter(new PorterDuffColorFilter(getColor(R.color.base_theme1), PorterDuff.Mode.SRC_IN));

        viewPager2.setAdapter(viewPagerAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if(position==0){
                tab.setIcon(R.drawable.ic_customers).setText("CUSTOMERS");
            } else {
                tab.setIcon(R.drawable.ic_transactions).setText("TRANSACTIONS");
            }
        });
        tabLayoutMediator.attach();

        setSupportActionBar(toolbar);
        viewPager2.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);

    }

}