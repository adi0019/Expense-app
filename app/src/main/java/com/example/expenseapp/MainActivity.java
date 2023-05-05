package com.example.expenseapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.expenseapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;
    ExpenseAdapter expenseAdapter;
    ExpensDatabase expenseDatabase;
    ExpenseDao expenseDao;
    long expense =0, income=0, due=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }
    protected void onResume(){
        super.onResume();
        expenseDatabase=ExpensDatabase.getInstance(this);
        expenseDao=expenseDatabase.getDao();
        expenseAdapter=new ExpenseAdapter(this);
        binding.itemsRecycler.setAdapter(expenseAdapter);
        binding.itemsRecycler.setLayoutManager(new LinearLayoutManager(this));

        List<ExpenseTable> expenseTables=expenseDao.getAll();

        for(int i=0; i<expenseTables.size(); i++) {
            if (expenseTables.get(i).isIncome()) {
                income = income + expenseTables.get(i).getAmount();
            }else {
                expense=expense+expenseTables.get(i).getAmount();
            }
                expenseAdapter.add(expenseTables.get(i));

        }
        binding.textView5.setText(expense+"");
        binding.textView3.setText(income+"");
        long balance = income-expense;
        binding.totalAmount.setText(balance+"");
        if(expense>income){
            long due = expense-income;
            binding.due.setText(due+"");
        }

    }
}