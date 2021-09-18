package com.example.shoppinglist.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppinglist.database.AppDatabase;
import com.example.shoppinglist.database.ShoppingRepo;
import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.model.ItemsInShoppingList;
import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ItemListViewModel extends AndroidViewModel {

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final ShoppingRepo shoppingRepo;
    private final MutableLiveData<List<Item>> items = new MutableLiveData<>();
    private ShoppingList shoppingList;

    public ItemListViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(application);
        shoppingRepo = new ShoppingRepo(database);
    }

    public void initModelView(int shoppingListId) {
        mDisposable.add(shoppingRepo.getItems(shoppingListId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData,
                        throwable -> Log.e("DB", "Unable to load item list", throwable)));
    }

    private void setData(ItemsInShoppingList itemsInShoppingList) {
        items.setValue(itemsInShoppingList.getItems());
        shoppingList = itemsInShoppingList.getShoppingList();
    }

    public Item getItem(int itemId) {
        for(Item i: items.getValue()) {
            if (i.getId() == itemId) {
                return i;
            }
        }
        return null;
    }

    public void insertItem(String itemName) {
        mDisposable.add(shoppingRepo.insertItem(new Item(itemName, shoppingList.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i("DB", "Successfully insert item"),
                        throwable -> Log.e("DB", "Unable to insert item", throwable)));
    }

    public void deleteItem(int itemId) {
        Item item = getItem(itemId);
        if (item != null) {
            mDisposable.add(shoppingRepo.deleteItem(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> Log.i("DB", "Successfully deleted item"),
                            throwable -> Log.e("DB", "Unable to delete item", throwable)));
        }
    }

    public void updateItem(int itemId, String itemName) {
        Item item = getItem(itemId);
        if (item != null) {
            item.setName(itemName);
            update(item);
        }
    }

    public void updateItem(int itemId, boolean isDone) {
        Item item = getItem(itemId);
        if (item != null) {
            item.setDone(isDone);
            System.out.println("done? " + isDone);
            update(item);
        }
    }

    private void update(Item item) {
        mDisposable.add(shoppingRepo.updateItem(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i("DB", "Successfully updated item"),
                        throwable -> Log.e("DB", "Unable to update item", throwable)));
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }
}
