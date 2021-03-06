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

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ArchiveShoppingListViewModel extends AndroidViewModel {

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final ShoppingRepo shoppingRepo;
    private final MutableLiveData<List<ShoppingList>> shoppingList = new MutableLiveData<>();

    public ArchiveShoppingListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        shoppingRepo = new ShoppingRepo(database);

        initShoppingLists();
    }

    private void initShoppingLists() {
        mDisposable.add(shoppingRepo.getShoppingLists(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shoppingList::setValue,
                        throwable -> Log.e("DB", "Unable to load shopping lists", throwable)));
    }

    public LiveData<List<ShoppingList>> getShoppingLists() {
        return shoppingList;
    }
}
