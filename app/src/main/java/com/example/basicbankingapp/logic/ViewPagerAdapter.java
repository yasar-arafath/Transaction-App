package com.example.basicbankingapp.logic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.basicbankingapp.ui.CustomerFragment;
import com.example.basicbankingapp.ui.TransactionFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;
    private Context context;
    private static FragmentActivity fragmentActivity;

    public static FragmentActivity getFragmentActivity() {
        return fragmentActivity;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.context = context;
        fragmentList = new ArrayList<Fragment>(){
            {
                add(new CustomerFragment());
                add(new TransactionFragment());
            }
        };
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

}
