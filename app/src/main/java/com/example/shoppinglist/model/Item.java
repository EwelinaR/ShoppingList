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
    private String name;
    private boolean isDone = false;

    public Item(String name) {
        this.name = name;
    }
}
