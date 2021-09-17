package com.example.shoppinglist.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "shopping_list")
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String name;
    @Ignore
    private List<Item> items;

    public ShoppingList(String date, String name) {
        this.date = date;
        this.name = name;
    }
}
