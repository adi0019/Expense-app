package com.example.expenseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private Context context;
    private List<ExpenseTable> expenseTableList;

    public ExpenseAdapter(Context context){
        this.context =context;
        expenseTableList=new ArrayList<>();
    }
    public void add(ExpenseTable expenseTable){
        expenseTableList.add(expenseTable);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExpenseTable expenseTable=expenseTableList.get(position);
        holder.title.setText(expenseTable.getPaymentType());
        holder.description.setText(expenseTable.getDescription());
        holder.amount.setText(String.valueOf(expenseTable.getAmount()));

        if(expenseTable.isIncome()){
            holder.status.setText("Income");
        }else{
            holder.status.setText("Expense");
        }

    }

    @Override
    public int getItemCount() {
        return expenseTableList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
         TextView status, title, description, amount;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            status=itemView.findViewById(R.id.isIncome);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            amount=itemView.findViewById(R.id.amount);

        }
    }
}
