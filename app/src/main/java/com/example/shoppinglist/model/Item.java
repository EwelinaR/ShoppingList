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
@Entity
@TypeConverters(DateConverter.class)
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int shoppingListId;
    private String name;
    private boolean isChecked = false;
    private Date date;

    public Item(Date date, String name, int shoppingListId) {
        this.date = date;
        this.name = name;
        this.shoppingListId = shoppingListId;
    }

    // getters / setters required for Kotlin class (ItemAdapter.kt)
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Date getDate() {
        return date;
    }
}
