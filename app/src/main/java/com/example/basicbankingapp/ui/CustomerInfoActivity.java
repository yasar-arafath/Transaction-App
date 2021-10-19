package com.example.basicbankingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.databinding.ActivityCustomerInfoBinding;
import com.example.basicbankingapp.logic.MakeTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerInfoActivity extends AppCompatActivity {

    private ActivityCustomerInfoBinding infoBinding;
    private Customer customer;
    private Customer receiver;
    private TextView accNum;
    private TextView balance;
    private TextView customerName;
    private TextView email;
    private TextView mobNum;
    private TextView dob;
    private TextView address;
    private EditText enterAmount;
    private Spinner spinner;
    private Button transactButton;
    private Button cancelButton;
    private ArrayList<String> arrayList;
    private List<Long> receiverIdList;
    private double transferAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoBinding = ActivityCustomerInfoBinding.inflate(getLayoutInflater());
        setContentView(infoBinding.getRoot());

        initialise();

    }

    public void initialise(){
        accNum = infoBinding.accNumInfo;
        balance = infoBinding.balanceInfo;
        customerName = infoBinding.customerNameInfo;
        email = infoBinding.emailInfo;
        mobNum = infoBinding.mobNumInfo;
        dob = infoBinding.dobInfo;
        address = infoBinding.addressInfo;
        spinner = infoBinding.dropDownList;
        enterAmount = infoBinding.enterAmount;
        transactButton = infoBinding.transactButton;
        cancelButton = infoBinding.cancelButton;

        receiverIdList = new ArrayList<>();

        Intent intent = getIntent();
        long customerID = intent.getLongExtra(CustomerFragment.GET_CUSTOMER_ID,-1);
        if(customerID != -1){
            Customers customers = new Customers(this);
            customer = customers.detailOfCustomer(customerID);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        accNum.setText(String.valueOf(customer.getAccID()));
        balance.setText("₹" + String.valueOf(customer.getBalance()));
        customerName.setText(customer.getName());
        email.setText(customer.getEmail());
        mobNum.setText(String.valueOf(customer.getMobNum()));
        dob.setText(dateFormat.format(new Date(customer.getDob())));
        address.setText(customer.getAddress());

        enterAmount.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                transactButton.setEnabled(!arg0.toString().isEmpty());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            public void afterTextChanged(Editable arg0) {
                String str = enterAmount.getText().toString();
                if (str.isEmpty()) return;
                String str2 = PerfectDecimal(str, 10, 2);

                if (!str2.equals(str)) {
                    enterAmount.setText(str2);
                    int pos = enterAmount.getText().length();
                    enterAmount.setSelection(pos);
                }
                transferAmount = Double.parseDouble(str2);
            }
        });

        arrayList = new ArrayList<>();
        arrayList.add("Select a beneficiary...");
        for(Customer customer: Customer.getDefaultCustomerList()){
            if(customerID!=customer.getAccID()) {
                arrayList.add(customer.getName());
                receiverIdList.add(customer.getAccID());
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    spinner.setAlpha(0.5f);
                    enterAmount.setEnabled(false);
                    transactButton.setEnabled(false);
                } else {
                    spinner.setAlpha(1);
                    enterAmount.setEnabled(true);
                    Customers customers = new Customers(getApplicationContext());
                    receiver = customers.detailOfCustomer(receiverIdList.get(position-1));
                    Toast.makeText(parent.getContext(), "Selected: " + receiver.getName(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        cancelButton.setOnClickListener(v -> finish());

        transactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeTransaction makeTransaction = new MakeTransaction(getApplicationContext(),new Transaction(null,customerID,receiver.getAccID(),0,transferAmount));
                Intent transactResult = new Intent(getApplicationContext(), TransactionResult.class);
                if(makeTransaction.make()){
                    transactResult.putExtra(TransactionResult.EXTRA,TransactionResult.SUCCESS);
                }else {
                    transactResult.putExtra(TransactionResult.EXTRA,TransactionResult.FAILURE);
                }
                startActivity(transactResult);
                finish();
            }
        });
    }

    public String PerfectDecimal(String str, int MAX_BEFORE_POINT, int MAX_DECIMAL){
        if(str.charAt(0) == '.') str = "0"+str;
        int max = str.length();

        String rFinal = "";
        boolean after = false;
        int i = 0, up = 0, decimal = 0; char t;
        while(i < max){
            t = str.charAt(i);
            if(t != '.' && after == false){
                up++;
                if(up > MAX_BEFORE_POINT) return rFinal;
            }else if(t == '.'){
                after = true;
            }else{
                decimal++;
                if(decimal > MAX_DECIMAL)
                    return rFinal;
            }
            rFinal = rFinal + t;
            i++;
        }return rFinal;
    }
}