package com.example.shoppinglist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;
import com.example.shoppinglist.adapter.ShoppingAdapter;
import com.example.shoppinglist.model.ShoppingList;
import com.example.shoppinglist.viewmodel.ShoppingListViewModel;

import java.util.List;

public class ShoppingListView extends Fragment implements Observer {

    private ShoppingListViewModel shoppingListViewModel;

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

        shoppingListViewModel.getShoppingLists()
                .observe(getViewLifecycleOwner(), shopping -> updateRecyclerView(view, shopping));
    }

    private void updateRecyclerView(View view, List<ShoppingList> shoppingList) {
        ShoppingAdapter adapter = new ShoppingAdapter(shoppingList, this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onClickItem(int itemId) {
        ItemListView fragment = new ItemListView();
        Bundle bundle = new Bundle();
        bundle.putInt("ITEM_ID", itemId);
        fragment.setArguments(bundle);
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment).addToBackStack(null);
        ft.commit();
    }
}
