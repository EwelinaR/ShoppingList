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

import com.example.shoppinglist.MainActivity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.adapter.ArchiveAdapter;
import com.example.shoppinglist.model.ShoppingList;
import com.example.shoppinglist.viewmodel.ArchiveShoppingListViewModel;

import java.util.List;

public class ArchiveShoppingListView extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.archive_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArchiveShoppingListViewModel archiveShoppingListViewModel = new ViewModelProvider(this)
                .get(ArchiveShoppingListViewModel.class);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.archive_lists_title);

        archiveShoppingListViewModel.getShoppingLists()
                .observe(getViewLifecycleOwner(), shoppingLists -> updateRecyclerView(view, shoppingLists));
    }

    private void updateRecyclerView(View view, List<ShoppingList> shoppingList) {
        ArchiveAdapter archiveAdapter = new ArchiveAdapter(shoppingList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listItem);
        recyclerView.setAdapter(archiveAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
