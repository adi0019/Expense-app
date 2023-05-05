package com.example.expenseapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.expenseapp.databinding.ActivityAddBinding;

import java.security.AccessController;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding=ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = binding.amount.getText().toString();
                String type = binding.paymentType.getText().toString();
                String desc=binding.description.getText().toString();
                boolean isIncome =binding.incomeRadio.isChecked();

                ExpenseTable expenseTable = new ExpenseTable();

                expenseTable.setAmount(Long.parseLong(amount));
                expenseTable.setDescription(desc);
                expenseTable.setIncome(isIncome);
                expenseTable.setPaymentType(type);


                ExpensDatabase expensDatabase = ExpensDatabase.getInstance(view.getContext());
                ExpenseDao expenseDao = expensDatabase.getDao();

                expenseDao.insertExpense(expenseTable);
                finish();



            }
        });
    }
}
