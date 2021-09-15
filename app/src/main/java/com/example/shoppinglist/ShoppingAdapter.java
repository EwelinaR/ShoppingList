package com.example.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shoppinglist.model.ShoppingList;

import java.util.ArrayList;

public class ShoppingAdapter  extends ArrayAdapter<ShoppingList> {
    public ShoppingAdapter(Context context, ArrayList<ShoppingList> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShoppingList shoppingList = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.shopping_list, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        name.setText(shoppingList.getName());
        date.setText(shoppingList.getDate());

        return convertView;
    }
}