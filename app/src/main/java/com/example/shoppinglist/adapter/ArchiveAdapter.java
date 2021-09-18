package com.example.shoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

public class ArchiveAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private final List<ShoppingList> shoppingLists;

    public ArchiveAdapter(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.shopping_list, parent, false);
        return new ShoppingAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingAdapter.ViewHolder holder, int position) {
        ShoppingList shoppingList = shoppingLists.get(position);
        holder.getName().setText(shoppingList.getName());
        holder.getDate().setText(shoppingList.getDate());
        holder.itemView.setOnClickListener(view -> { });
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }
}
