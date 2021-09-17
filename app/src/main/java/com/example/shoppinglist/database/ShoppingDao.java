package com.example.shoppinglist.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ShoppingDao {
    @Query("SELECT * FROM shopping_list")
    Flowable<List<ShoppingList>> getAllShoppingLists();

    @Insert
    Completable insertShoppingList(ShoppingList shoppingList);
}
