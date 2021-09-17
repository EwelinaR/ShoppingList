package com.example.shoppinglist.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.model.ItemsInShoppingList;
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

    @Delete
    Completable deleteShoppingList(ShoppingList shoppingList);

    @Query("SELECT * FROM shopping_list WHERE shopping_list.id = :id")
    @Transaction
    Flowable<ItemsInShoppingList> getItems(int id);

    @Insert
    Completable insertItem(Item item);

    @Update
    Completable updateItem(Item item);

    @Delete
    Completable deleteItem(Item item);
}
