package com.example.shoppinglist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.adapter.ItemAdapter;
import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;
import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.viewmodel.ItemListViewModel;

import java.util.List;

public class ItemListView extends Fragment implements Observer {

    private ItemListViewModel itemListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemListViewModel = new ViewModelProvider(this).get(ItemListViewModel.class);

        int id = getArguments().getInt("ITEM_ID");
        itemListViewModel.initModelView(id);
        itemListViewModel.getItems()
                .observe(getViewLifecycleOwner(), shopping -> updateRecyclerView(view, shopping));
    }

    private void updateRecyclerView(View view, List<Item> items) {
        ItemAdapter adapter = new ItemAdapter(items, this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onClickItem(int itemId) {

    }
}
