package com.example.shoppinglist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.shoppinglist.DateConverter;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "shopping_list")
@TypeConverters(DateConverter.class)
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String name;
    private boolean isArchived = false;

    public ShoppingList(Date date, String name) {
        this.date = date;
        this.name = name;
    }
}
