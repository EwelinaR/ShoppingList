package com.example.shoppinglist.adapter;

import androidx.annotation.NonNull;

import com.example.shoppinglist.Observer;
import com.example.shoppinglist.model.ShoppingList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingAdapter extends ArchiveAdapter implements Adapter {
    private final Observer observer;

    public ShoppingAdapter(Observer observer) {
        super(new ArrayList<>());
        this.observer = observer;
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(
                view -> observer.onClickItem(shoppingLists.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public void submitItems(List<ShoppingList> newShoppingLists) {
        Collections.sort(newShoppingLists, (t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        shoppingLists.clear();
        shoppingLists.addAll(newShoppingLists);
        notifyDataSetChanged();
    }

    @Override
    public void deleteItem(int position) {
        int itemId = shoppingLists.get(position).getId();
        shoppingLists.remove(position);
        notifyItemRemoved(position);
        observer.delete(itemId);
    }
}
