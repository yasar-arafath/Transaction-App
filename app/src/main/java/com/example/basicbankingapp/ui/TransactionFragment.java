package com.example.basicbankingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Transactions;
import com.example.basicbankingapp.logic.TransactionAdapter;
import com.example.basicbankingapp.logic.ViewPagerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context context;
    private ListView listOfTransactions;
    private Button sortButton;
    public static final String GET_TRANSACTION_ID = "TRANSACTION_ID";

    private String mParam1;
    private String mParam2;

    public TransactionFragment() {
        context = ViewPagerAdapter.getFragmentActivity().getApplicationContext();
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionFragment.
     */
    public static TransactionFragment newInstance(String param1, String param2, Context context) {
        TransactionFragment fragment = new TransactionFragment();
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
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialise();
    }

    private void initialise(){
        listOfTransactions = (ListView) this.requireView().findViewById(R.id.listOfTransactions);
        sortButton = (Button) this.requireView().findViewById(R.id.sortButton);
        listOfTransactions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TransactionAdapter.ViewHolder viewHolder = (TransactionAdapter.ViewHolder) view.getTag();
                Intent transactionInfo = new Intent(context,TransactionInfoActivity.class);
                transactionInfo.putExtra(GET_TRANSACTION_ID,viewHolder.getTransactionId());
                startActivity(transactionInfo);
            }
        });
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = v.getTag().toString();
                if(order.equals(Transactions.ASCENDING)){
                    sortButton.setText("OLDEST FIRST");
                    v.setTag(Transactions.DESCENDING);
                    Transactions.setORDER(Transactions.DESCENDING);
                } else {
                    sortButton.setText("LATEST FIRST");
                    v.setTag(Transactions.ASCENDING);
                    Transactions.setORDER(Transactions.ASCENDING);
                }
                updateTransactionList();
            }
        });
        updateTransactionList();
    }

    private void updateTransactionList(){
        Transactions transactions = new Transactions(context);
        List<Transaction> allTransactions = transactions.allTransactions();

        TransactionAdapter listAdapter = new TransactionAdapter(context, R.layout.activity_transaction_list,allTransactions);
        listOfTransactions.setAdapter(listAdapter);
    }

}