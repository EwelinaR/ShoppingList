package com.example.shoppinglist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.ShoppingAdapter;
import com.example.shoppinglist.model.ShoppingList;

import java.util.ArrayList;

public class ShoppingListView extends Fragment {

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

        initShoppingList(view);
    }

    private void initShoppingList(View view) {
        ArrayList<ShoppingList> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new ShoppingList("20.03.2021", "Rossmann", null));
        arrayOfUsers.add(new ShoppingList("22.03.2021", "Auchan", null));

        ShoppingAdapter adapter = new ShoppingAdapter(getContext(), arrayOfUsers);

        ListView listView = (ListView) view.findViewById(R.id.listItem);
        listView.setAdapter(adapter);
    }
}
