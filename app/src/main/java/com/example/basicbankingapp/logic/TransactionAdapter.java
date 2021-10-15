package com.example.basicbankingapp.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.banking.Transaction;

import java.util.List;
import java.util.Objects;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private List<Transaction> transactions;
    private Context context;
    private int resource;

    static class ViewHolder{
        TextView sender;
        TextView receiver;
        TextView amount;
    }

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public TransactionAdapter(@NonNull Context context, int resource, @NonNull List<Transaction> objects) {
        super(context, resource);
        this.transactions = objects;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Nullable
    @Override
    public Transaction getItem(int position) {
        return transactions.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = new ViewHolder();
        View row = inflater.inflate(resource,parent,false);;
        viewHolder.sender = (TextView) Objects.requireNonNull(row).findViewById(R.id.senderName);
        viewHolder.receiver = (TextView) Objects.requireNonNull(row).findViewById(R.id.receiverName);
        viewHolder.amount = (TextView) Objects.requireNonNull(row).findViewById(R.id.transactionAmount);

        row.setTag(viewHolder);
        viewHolder.sender.setText(String.valueOf(transactions.get(position).getSenderAcc()));
        viewHolder.receiver.setText(String.valueOf(transactions.get(position).getReceiverAcc()));
        viewHolder.amount.setText(String.format("₹%s", String.valueOf(transactions.get(position).getAmount())));
        return row;
    }

}
