package com.example.shoppinglist.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppinglist.database.AppDatabase;
import com.example.shoppinglist.database.ShoppingRepo;
import com.example.shoppinglist.model.ShoppingList;

import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListViewModel extends AndroidViewModel {

    private static final String TAG = "DB";
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final ShoppingRepo shoppingRepo;
    private final MutableLiveData<List<ShoppingList>> shoppingList = new MutableLiveData<>();

    public ShoppingListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        shoppingRepo = new ShoppingRepo(database);

        initShoppingLists();
    }

    private void initShoppingLists() {
        mDisposable.add(shoppingRepo.getShoppingLists(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shoppingList::setValue,
                        throwable -> Log.e(TAG, "Unable to load shopping lists", throwable)));
    }

    public ShoppingList getShoppingList(int itemId) {
        for(ShoppingList i: shoppingList.getValue()) {
            if (i.getId() == itemId) {
                return i;
            }
        }
        return null;
    }

    public void insertShoppingList(String name) {
        mDisposable.add(shoppingRepo.insertShoppingList(
                new ShoppingList(Calendar.getInstance().getTime(), name))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> Log.i(TAG, "Successfully insert shopping list"),
                            throwable -> Log.e(TAG, "Unable to insert shopping list", throwable)));
    }

    public void archiveShoppingList(int itemId) {
        ShoppingList shoppingList = getShoppingList(itemId);
        if (shoppingList != null) {
            shoppingList.setArchived(true);
            mDisposable.add(shoppingRepo.updateShoppingList(shoppingList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> Log.i(TAG, "Successfully deleted shopping list"),
                            throwable -> Log.e(TAG, "Unable to delete shopping list", throwable)));
        }
    }

    public LiveData<List<ShoppingList>> getShoppingLists() {
        return shoppingList;
    }

    @Override
    public void onCleared() {
        mDisposable.clear();
    }
}
