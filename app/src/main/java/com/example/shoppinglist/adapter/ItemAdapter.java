package com.example.shoppinglist.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.ItemObserver;
import com.example.shoppinglist.R;
import com.example.shoppinglist.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Adapter {
    private final List<Item> items;
    private final ItemObserver observer;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final CheckBox isChecked;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            isChecked = (CheckBox) view.findViewById(R.id.isChecked);
        }
    }

    public ItemAdapter(List<Item> items, ItemObserver observer) {
        this.items = items;
        this.observer = observer;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.items_list, parent, false);
        return new ItemAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        holder.isChecked.setChecked(item.isChecked());
        holder.isChecked.setOnClickListener(view -> {
            item.setChecked(holder.isChecked.isChecked());
            notifyItemChanged(position);
            observer.isChecked(item.getId(), item.isChecked());
        });
        holder.itemView.setOnClickListener(view -> observer.onClickItem(item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void submitItems(List<Item> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void deleteItem(int position) {
        int itemId = items.get(position).getId();
        items.remove(position);
        notifyItemRemoved(position);
        observer.delete(itemId);
    }
}
