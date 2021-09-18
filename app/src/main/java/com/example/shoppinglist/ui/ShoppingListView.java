package com.example.shoppinglist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.MainActivity;
import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;
import com.example.shoppinglist.adapter.SwipeToDeleteCallback;
import com.example.shoppinglist.adapter.ShoppingAdapter;
import com.example.shoppinglist.model.ShoppingList;
import com.example.shoppinglist.viewmodel.ShoppingListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListView extends Fragment implements Observer {

    private ShoppingListViewModel shoppingListViewModel;
    private ShoppingAdapter shoppingAdapter;

    public static ShoppingListView newInstance() {
        return new ShoppingListView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.shopping_lists_title);

        initRecyclerView(view);

        shoppingListViewModel.getShoppingLists()
                .observe(getViewLifecycleOwner(), this::updateRecyclerView);

        FloatingActionButton fab = view.findViewById(R.id.addItem);
        fab.setOnClickListener(view1 -> showAddItemDialog());
    }

    void initRecyclerView(View view) {
        shoppingAdapter = new ShoppingAdapter( this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listItem);
        recyclerView.setAdapter(shoppingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(new SwipeToDeleteCallback(shoppingAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    void showAddItemDialog() {
        AddItemDialog dialog = new AddItemDialog(this);
        dialog.show(getParentFragmentManager(), "AddDialogFragment");
    }

    private void updateRecyclerView(List<ShoppingList> shoppingList) {
        shoppingAdapter.submitItems(shoppingList);
    }

    @Override
    public void onClickItem(int itemId) {
        ItemListView fragment = new ItemListView();
        Bundle bundle = new Bundle();
        bundle.putInt("ITEM_ID", itemId);
        bundle.putString("SHOPPING_LIST", shoppingListViewModel.getShoppingList(itemId).getName());
        fragment.setArguments(bundle);
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void add(String name) {
        shoppingListViewModel.insertShoppingList(name);
    }

    @Override
    public void update(int itemId, String name) { }

    @Override
    public void delete(int itemId) {
        shoppingListViewModel.archiveShoppingList(itemId);
    }
}
