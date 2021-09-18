package com.example.shoppinglist.database;


import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.model.ItemsInShoppingList;
import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ShoppingRepo {
    private final ShoppingDao shoppingDao;

    public ShoppingRepo(AppDatabase database) {
        shoppingDao = database.shoppingDao();
    }

    public Flowable<List<ShoppingList>> getShoppingLists(boolean isArchived) {
        return shoppingDao.getShoppingLists(isArchived);
    }

    public Completable insertShoppingList(ShoppingList shoppingList) {
        return shoppingDao.insertShoppingList(shoppingList);
    }

    public Completable updateShoppingList(ShoppingList shoppingList) {
        return shoppingDao.updateShoppingList(shoppingList);
    }

    public Flowable<ItemsInShoppingList> getItems(int id) {
        return shoppingDao.getItems(id);
    }

    public Completable insertItem(Item item) {
        return shoppingDao.insertItem(item);
    }

    public Completable updateItem(Item item) {
        return shoppingDao.updateItem(item);
    }

    public Completable deleteItem(Item item) {
        return shoppingDao.deleteItem(item);
    }
}
