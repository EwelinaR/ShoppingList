package com.example.shoppinglist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    private boolean isArchived = false;

    public ShoppingList(String date, String name) {
        this.date = date;
        this.name = name;
    }
}
