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
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;

import java.util.List;
import java.util.Objects;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private final List<Transaction> transactions;
    private final Context context;
    private final int resource;

    public static class ViewHolder{
        TextView senderId;
        TextView receiverId;
        TextView amount;
        TextView senderName;
        TextView receiverName;
        String transactionId;

        public String getTransactionId() {
            return transactionId;
        }
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
        View row = inflater.inflate(resource,parent,false);
        viewHolder.transactionId = transactions.get(position).getTransactionId();
        viewHolder.senderId = Objects.requireNonNull(row).findViewById(R.id.senderID);
        viewHolder.receiverId = Objects.requireNonNull(row).findViewById(R.id.receiverID);
        viewHolder.amount = Objects.requireNonNull(row).findViewById(R.id.transactionAmount);
        viewHolder.senderName = Objects.requireNonNull(row).findViewById(R.id.senderName);
        viewHolder.receiverName = Objects.requireNonNull(row).findViewById(R.id.receiverName);

        row.setTag(viewHolder);
        viewHolder.senderId.setText(String.valueOf(transactions.get(position).getSenderAcc()));
        viewHolder.receiverId.setText(String.valueOf(transactions.get(position).getReceiverAcc()));
        viewHolder.amount.setText(String.format("₹%s", transactions.get(position).getAmount()));
        Customers customers = new Customers(context);
        viewHolder.senderName.setText(customers.detailOfCustomer(transactions.get(position).getSenderAcc()).getName());
        viewHolder.receiverName.setText(customers.detailOfCustomer(transactions.get(position).getReceiverAcc()).getName());
        return row;
    }

}
