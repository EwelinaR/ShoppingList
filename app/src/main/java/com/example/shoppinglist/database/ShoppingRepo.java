package com.example.shoppinglist.database;


import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ShoppingRepo {
    private final ShoppingDao shoppingDao;

    public ShoppingRepo(AppDatabase database) {
        shoppingDao = database.shoppingDao();
    }

    public Flowable<List<ShoppingList>> getAllShoppingLists() {
        return shoppingDao.getAllShoppingLists();
    }

    public Completable insertShoppingList(ShoppingList shoppingList) {
        return shoppingDao.insertShoppingList(shoppingList);
    }
}
