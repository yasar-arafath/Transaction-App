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

import java.util.List;
import java.util.Objects;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    private final List<Customer> customers;
    private final Context context;
    private final int resource;

    public static class ViewHolder{
        TextView customerName;
        TextView email;
        TextView balance;
        long customerId;

        public long getCustomerId() {
            return customerId;
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
    public CustomerAdapter(@NonNull Context context, int resource, @NonNull List<Customer> objects) {
        super(context, resource);
        this.customers = objects;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Nullable
    @Override
    public Customer getItem(int position) {
        return customers.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = new ViewHolder();
        View row = inflater.inflate(resource,parent,false);
        viewHolder.customerId = customers.get(position).getAccID();
        viewHolder.customerName = Objects.requireNonNull(row).findViewById(R.id.customerName);
        viewHolder.email = Objects.requireNonNull(row).findViewById(R.id.email);
        viewHolder.balance = Objects.requireNonNull(row).findViewById(R.id.balance);

        row.setTag(viewHolder);
        viewHolder.customerName.setText(customers.get(position).getName());
        viewHolder.email.setText(String.valueOf(customers.get(position).getEmail()));
        viewHolder.balance.setText(String.format("₹%s", customers.get(position).getBalance()));
        return row;
    }

}
