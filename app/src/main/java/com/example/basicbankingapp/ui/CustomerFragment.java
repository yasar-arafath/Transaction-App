package com.example.basicbankingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.logic.CustomerAdapter;
import com.example.basicbankingapp.logic.ViewPagerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context context;
    private ListView listOfCustomers;
    public static final String GET_CUSTOMER_ID = "CUSTOMER_ID";

    private String mParam1;
    private String mParam2;

    public CustomerFragment() {
        context = ViewPagerAdapter.getFragmentActivity().getApplicationContext();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerFragment.
     */
    public static CustomerFragment newInstance(String param1, String param2, Context context) {
        CustomerFragment fragment = new CustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialise();
    }

    private void initialise(){
        listOfCustomers = (ListView) this.requireView().findViewById(R.id.listOfCustomers);
        listOfCustomers.setOnItemClickListener((parent, view, position, id) -> {
            CustomerAdapter.ViewHolder viewHolder = (CustomerAdapter.ViewHolder) view.getTag();
            Intent customerInfo = new Intent(context, CustomerInfoActivity.class);
            customerInfo.putExtra(GET_CUSTOMER_ID,viewHolder.getCustomerId());
            startActivity(customerInfo);
        });
        updateCustomerList();
    }

    private void updateCustomerList(){
        Customers customers = new Customers(context);
        List<Customer> allCustomers = customers.allCustomers();

        CustomerAdapter listAdapter = new CustomerAdapter(context, R.layout.activity_customer_list,allCustomers);
        listOfCustomers.setAdapter(listAdapter);
    }

}