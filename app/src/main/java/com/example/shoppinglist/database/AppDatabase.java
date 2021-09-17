package com.example.shoppinglist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.model.ShoppingList;

@Database(entities = {ShoppingList.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract ShoppingDao shoppingDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class , "ShoppingList")
                    .build();
        }
        return instance;
    }
}
