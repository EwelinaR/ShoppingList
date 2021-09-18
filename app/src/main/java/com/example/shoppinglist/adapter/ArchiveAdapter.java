package com.example.shoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.model.ShoppingList;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Getter;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ViewHolder> {

    final List<ShoppingList> shoppingLists;

    public ArchiveAdapter(List<ShoppingList> shoppingLists) {
        Collections.sort(shoppingLists, (t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        this.shoppingLists = shoppingLists;
    }

    @NonNull
    @Override
    public ArchiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.shopping_list, parent, false);
        return new ArchiveAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveAdapter.ViewHolder holder, int position) {
        ShoppingList shoppingList = shoppingLists.get(position);
        holder.getName().setText(shoppingList.getName());
        holder.getDate().setText(getFormattedData(shoppingList.getDate()));
        holder.itemView.setOnClickListener(view -> { });
    }

    private String getFormattedData(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        if (date.before(cal.getTime())) {
            return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        } else {
            return   DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
        }
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

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
}
