package com.example.expenseapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ExpenseTable.class}, version = 1)
abstract class ExpensDatabase extends RoomDatabase{
    public abstract ExpenseDao getDao();
    public static volatile ExpensDatabase INSTANCE;

    public static  ExpensDatabase getInstance(Context context){
        if(INSTANCE ==null){
           INSTANCE = Room.databaseBuilder(context,ExpensDatabase.class, "expense")
                   .allowMainThreadQueries()
                   .fallbackToDestructiveMigration()
                   .build();

        }
        return INSTANCE;
    }
}



