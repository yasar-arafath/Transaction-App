package com.example.basicbankingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.database.Transactions;
import com.example.basicbankingapp.databinding.ActivityTransactionInfoBinding;

public class TransactionInfoActivity extends AppCompatActivity {

    private ActivityTransactionInfoBinding infoBinding;
    private Transaction transaction;
    private TextView transactionId;
    private TextView transactAmount;
    private TextView senderId;
    private TextView senderName;
    private TextView receiverId;
    private TextView receiverName;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoBinding = ActivityTransactionInfoBinding.inflate(getLayoutInflater());
        setContentView(infoBinding.getRoot());

        initialise();

    }

    public void initialise(){
        transactionId = infoBinding.transactID;
        transactAmount = infoBinding.transactAmtInfo;
        senderId = infoBinding.senderIdInfo;
        senderName = infoBinding.senderNameInfo;
        receiverId = infoBinding.receiverIdInfo;
        receiverName = infoBinding.receiverNameInfo;
        backBtn = infoBinding.backButton;

        Intent intent = getIntent();
        String transactionID = intent.getStringExtra(TransactionFragment.GET_TRANSACTION_ID);
        if(transactionID != null){
            Transactions transactions = new Transactions(this);
            transaction = transactions.detailOfTransaction(transactionID);
        }

        Customers customers = new Customers(this);

        transactionId.setText(String.valueOf(transaction.getTransactionId()));
        transactAmount.setText(String.format("₹%s", transaction.getAmount()));
        senderId.setText(String.valueOf(transaction.getSenderAcc()));
        senderName.setText(customers.detailOfCustomer(transaction.getSenderAcc()).getName());
        receiverId.setText(String.valueOf(transaction.getReceiverAcc()));
        receiverName.setText(customers.detailOfCustomer(transaction.getReceiverAcc()).getName());

        backBtn.setOnClickListener(v -> finish());

    }
}