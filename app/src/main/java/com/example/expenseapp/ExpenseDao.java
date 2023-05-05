package com.example.expenseapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insertExpense(ExpenseTable expenseTable);

    @Update
    void updateExpense(ExpenseTable expenseTable);

    @Query("delete from expense where id=:id")
    void delete(int id);

    @Query("Select * from expense")
    List<ExpenseTable> getAll();
}
