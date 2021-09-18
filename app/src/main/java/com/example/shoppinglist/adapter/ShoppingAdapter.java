package com.example.shoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.Observer;
import com.example.shoppinglist.R;
import com.example.shoppinglist.model.ShoppingList;

import java.util.List;

import lombok.Getter;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder>
        implements Adapter {
    private final List<ShoppingList> shoppingLists;
    private final Observer observer;

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView date;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public ShoppingAdapter(List<ShoppingList> shoppingLists, Observer observer) {
        this.shoppingLists = shoppingLists;
        this.observer = observer;
    }

    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.shopping_list, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingList shoppingList = shoppingLists.get(position);
        holder.name.setText(shoppingList.getName());
        holder.date.setText(shoppingList.getDate());
        holder.itemView.setOnClickListener(view -> observer.onClickItem(shoppingList.getId()));
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public void submitItems(List<ShoppingList> newShoppingLists) {
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
