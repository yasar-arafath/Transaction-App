package com.example.basicbankingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicbankingapp.databinding.ActivityTransactionResultBinding;

public class TransactionResult extends AppCompatActivity {

    ActivityTransactionResultBinding binding;
    private Button okButton;
    private ImageView successTick;
    private TextView resultMessage;

    public static final String EXTRA = "RESULT";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        okButton = binding.okButton;
        successTick = binding.success;
        resultMessage = binding.transactResult;

        Intent intent = getIntent();
        String result = intent.getStringExtra(EXTRA);

        if(result.equals(FAILURE)){
            successTick.setVisibility(View.GONE);
            resultMessage.setText("Transaction Unsuccessful!");
        } else {
            successTick.setVisibility(View.VISIBLE);
            resultMessage.setText("Transaction Successful!");
        }

        okButton.setOnClickListener(v -> finish());

    }
}