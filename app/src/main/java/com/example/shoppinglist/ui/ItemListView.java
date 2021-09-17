package com.example.shoppinglist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.adapter.ItemAdapter;
import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;
import com.example.shoppinglist.adapter.SwipeToDeleteCallback;
import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.viewmodel.ItemListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ItemListView extends Fragment implements Observer {

    private ItemListViewModel itemListViewModel;
    private ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemListViewModel = new ViewModelProvider(this).get(ItemListViewModel.class);

        initRecyclerView(view);

        int id = getArguments().getInt("ITEM_ID");
        itemListViewModel.initModelView(id);
        itemListViewModel.getItems().observe(getViewLifecycleOwner(), this::updateRecyclerView);

        FloatingActionButton fab = view.findViewById(R.id.addItem);
        fab.setOnClickListener(view1 -> showAddItemDialog());
    }

    void initRecyclerView(View view) {
        itemAdapter = new ItemAdapter(new ArrayList<>(), this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listItem);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(new SwipeToDeleteCallback(itemAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    void showAddItemDialog() {
        AddItemDialog dialog = new AddItemDialog(this);
        dialog.show(getParentFragmentManager(), "AddDialogFragment");
    }

    private void updateRecyclerView(List<Item> items) {
        itemAdapter.submitItems(items);
    }

    @Override
    public void onClickItem(int itemId) {
        for (Item i : itemListViewModel.getItems().getValue()) {
            if (i.getId() == itemId) {
                AddItemDialog dialog = new AddItemDialog(i.getId(), i.getName(), this);
                dialog.show(getParentFragmentManager(), "EditDialogFragment");
                return;
            }
        }
    }

    @Override
    public void add(String name) {
        itemListViewModel.insertItem(name);
    }

    @Override
    public void update(int itemId, String name) {
        itemListViewModel.updateItem(itemId, name);
    }

    @Override
    public void delete(int position) {
        itemListViewModel.deleteItem(position);
    }
}
