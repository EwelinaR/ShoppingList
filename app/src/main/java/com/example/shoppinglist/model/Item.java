package com.example.shoppinglist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int shoppingListId;
    private String name;
    private boolean isChecked = false;

    public Item(String name, int shoppingListId) {
        this.name = name;
        this.shoppingListId = shoppingListId;
    }
}
