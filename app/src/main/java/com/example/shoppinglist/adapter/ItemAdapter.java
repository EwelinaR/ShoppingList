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
import com.example.shoppinglist.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final List<Item> items;
    private final Observer observer;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView isDone;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            isDone = (TextView) view.findViewById(R.id.isDone);
        }
    }

    public ItemAdapter(List<Item> items, Observer observer) {
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
        holder.isDone.setText(String.valueOf(item.isDone()));
        holder.itemView.setOnClickListener(view -> observer.onClickItem(item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

